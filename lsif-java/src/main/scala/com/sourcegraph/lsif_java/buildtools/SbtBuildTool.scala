package com.sourcegraph.lsif_java.buildtools

import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.Properties

import scala.util.Using

import com.sourcegraph.io.AutoDeletedFile
import com.sourcegraph.lsif_java.BuildInfo
import com.sourcegraph.lsif_java.commands.IndexCommand

class SbtBuildTool(index: IndexCommand) extends BuildTool("sbt", index) {
  override def usedInCurrentDirectory(): Boolean = {
    Files.isRegularFile(index.workingDirectory.resolve("build.sbt")) ||
    sbtVersion().isDefined
  }

  override def generateLsif(): Int = {
    sbtVersion() match {
      case Some(version) =>
        if (isSupportedSbtVersion(version)) {
          unconditionallyGenerateLsif()
        } else {
          failFast(
            s"Unsupported sbt version '$version'. " +
              s"To fix this problem, upgrade to sbt ${BuildInfo.sbtVersion} and try again."
          )
        }
      case None =>
        failFast(
          s"No sbt version detected. " +
            s"To fix this problem, run the following command and try again: " +
            s"echo 'sbt.version=${BuildInfo.sbtVersion}' >> project/build.properties"
        )
    }
  }

  private def failFast(message: String): Int = {
    index.app.error(message)
    1
  }

  private def unconditionallyGenerateLsif(): Int =
    Using.resource(sourcegraphSbtPluginFile()) { _ =>
      val sourcegraphLsif = index
        .process(List("sbt", "sourcegraphEnable", "sourcegraphLsif"))
      val inputDump = index
        .workingDirectory
        .resolve("target")
        .resolve("sbt-sourcegraph")
        .resolve("dump.lsif")
      if (sourcegraphLsif.exitCode == 0 && Files.isRegularFile(inputDump)) {
        val outputDump = index.workingDirectory.resolve("dump.lsif")
        Files.copy(inputDump, outputDump, StandardCopyOption.REPLACE_EXISTING)
        index.app.info(outputDump.toString)
      }
      sourcegraphLsif.exitCode
    }

  private def isSupportedSbtVersion(version: String): Boolean = {
    (!version.startsWith("0.13") || version.startsWith("0.13.17")) &&
    !version.startsWith("1.0") && !version.startsWith("1.1")
  }

  private def sbtVersion(): Option[String] = {
    val buildProperties = index
      .workingDirectory
      .resolve("project")
      .resolve("build.properties")
    if (Files.isRegularFile(buildProperties)) {
      val props = new Properties()
      val in = Files.newInputStream(buildProperties)
      try props.load(in)
      finally in.close()
      Option(props.getProperty("sbt.version"))
    } else {
      None
    }
  }

  private def sourcegraphSbtPluginFile(): AutoDeletedFile = {
    val addSbtPluginFile = index
      .workingDirectory
      .resolve("project")
      .resolve("sourcegraph_generated.sbt")
    val version = BuildInfo.sbtSourcegraphVersion
    AutoDeletedFile.fromPath(
      addSbtPluginFile,
      s"""addSbtPlugin("com.sourcegraph" % "sbt-sourcegraph" % "$version")
         |""".stripMargin
    )
  }
}

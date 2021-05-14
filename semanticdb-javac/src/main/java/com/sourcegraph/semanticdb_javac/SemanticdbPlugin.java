package com.sourcegraph.semanticdb_javac;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

import com.sun.source.util.*;
import com.sun.tools.javac.api.BasicJavacTask;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.util.Context;

import static javax.tools.StandardLocation.CLASS_OUTPUT;

/** Entrypoint of the semanticdb-javac compiler plugin. */
public class SemanticdbPlugin implements Plugin {

  public static String stubClassName = "META-INF-stub";

  @Override
  public String getName() {
    return "semanticdb";
  }

  @Override
  public void init(JavacTask task, String... args) {
    Context ctx = ((BasicJavacTask) task).getContext();

    Path outputDir = null;
    try {
      JavaFileManager fm = ctx.get(JavaFileManager.class);
      FileObject outputDirStub =
          fm.getJavaFileForOutput(CLASS_OUTPUT, stubClassName, JavaFileObject.Kind.CLASS, null);
      outputDir = Paths.get(outputDirStub.toUri()).toAbsolutePath().getParent();
    } catch (Exception ignored) {
    }

    SemanticdbReporter reporter = new SemanticdbReporter();
    SemanticdbJavacOptions options = SemanticdbJavacOptions.parse(args, outputDir);
    GlobalSymbolsCache globals = new GlobalSymbolsCache(options);
    JavacTypes javacTypes = JavacTypes.instance(ctx);
    if (!options.errors.isEmpty()) {
      for (String error : options.errors) {
        reporter.error(error);
      }
    } else {
      task.addTaskListener(
          new SemanticdbTaskListener(options, task, globals, reporter, javacTypes));
    }
  }
}

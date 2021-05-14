package com.sourcegraph.semanticdb_javac;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/** Settings that can be configured alongside the -Xplugin compiler option. */
public class SemanticdbJavacOptions {

  /** The directory to place */
  public Path targetroot;

  public Path sourceroot;
  public boolean includeText = false;
  public boolean verboseEnabled = false;
  public final ArrayList<String> errors;

  public SemanticdbJavacOptions() {
    errors = new ArrayList<>();
  }

  public static String missingRequiredDirectoryOption(String option) {
    return String.format(
        "missing argument '-%s'. To fix this problem, update the Java compiler option "
            + "'-Xplugin:semanticdb -%s:DIRECTORY' where DIRECTORY is the path to a valid directory.",
        option, option);
  }

  public static SemanticdbJavacOptions parse(String[] args, Path defaultTargetRoot) {
    SemanticdbJavacOptions result = new SemanticdbJavacOptions();
    for (String arg : args) {
      if (arg.startsWith("-targetroot:")) {
        result.targetroot = Paths.get(arg.substring("-targetroot:".length()));
      } else if (arg.startsWith("-sourceroot:")) {
        result.sourceroot = Paths.get(arg.substring("-sourceroot:".length())).normalize();
      } else if (arg.equals("-text:on")) {
        result.includeText = true;
      } else if (arg.equals("-text:off")) {
        result.includeText = false;
      } else if (arg.equals("-verbose")) {
        result.verboseEnabled = true;
      } else if (arg.equals("-verbose:on")) {
        result.verboseEnabled = true;
      } else if (arg.equals("-verbose:off")) {
        result.verboseEnabled = false;
      } else {
        result.errors.add(String.format("unknown flag '%s'\n", arg));
      }
    }
    if (result.targetroot == null) {
      if (defaultTargetRoot == null) {
        result.errors.add(missingRequiredDirectoryOption("targetroot"));
      }
      result.targetroot = defaultTargetRoot;
    }
    if (result.sourceroot == null) {
      result.errors.add(missingRequiredDirectoryOption("sourceroot"));
    }
    return result;
  }
}

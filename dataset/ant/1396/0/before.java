class PlaceHold {
  protected Commandline setupKjcCommand() {
    Commandline cmd = new Commandline();
    Path classpath = getCompileClasspath();
    if (deprecation == true) {
      cmd.createArgument().setValue("-deprecation");
    }
    if (destDir != null) {
      cmd.createArgument().setValue("-d");
      cmd.createArgument().setFile(destDir);
    }
    cmd.createArgument().setValue("-classpath");
    Path cp = new Path(project);
    if (bootclasspath != null) {
      cp.append(bootclasspath);
    }
    if (extdirs != null) {
      cp.addExtdirs(extdirs);
    }
    cp.append(classpath);
    if (compileSourcepath != null) {
      cp.append(compileSourcepath);
    } else {
      cp.append(src);
    }
    cmd.createArgument().setPath(cp);
    if (encoding != null) {
      cmd.createArgument().setValue("-encoding");
      cmd.createArgument().setValue(encoding);
    }
    if (debug) {
      cmd.createArgument().setValue("-g");
    }
    if (optimize) {
      cmd.createArgument().setValue("-O2");
    }
    if (verbose) {
      cmd.createArgument().setValue("-verbose");
    }
    addCurrentCompilerArgs(cmd);
    logAndAddFilesToCompile(cmd);
    return cmd;
  }
}

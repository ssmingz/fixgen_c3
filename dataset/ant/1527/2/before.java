class PlaceHold {
  protected Commandline setupGCJCommand() {
    Commandline cmd = new Commandline();
    Path classpath = new Path(project);
    if (bootclasspath != null) {
      classpath.append(bootclasspath);
    }
    classpath.addExtdirs(extdirs);
    if ((bootclasspath == null) || (bootclasspath.size() == 0)) {
      includeJavaRuntime = true;
    }
    classpath.append(getCompileClasspath());
    classpath.append(src);
    cmd.setExecutable("gcj");
    if (destDir != null) {
      cmd.createArgument().setValue("-d");
      cmd.createArgument().setFile(destDir);
      if (destDir.mkdirs()) {
        throw new BuildException("Can't make output directories. " + "Maybe permission is wrong. ");
      }
    }
    cmd.createArgument().setValue("-classpath");
    cmd.createArgument().setPath(classpath);
    if (encoding != null) {
      cmd.createArgument().setValue("--encoding=" + encoding);
    }
    if (debug) {
      cmd.createArgument().setValue("-g1");
    }
    if (optimize) {
      cmd.createArgument().setValue("-O");
    }
    cmd.createArgument().setValue("-C");
    addCurrentCompilerArgs(cmd);
    return cmd;
  }
}

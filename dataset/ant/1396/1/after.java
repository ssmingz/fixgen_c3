class PlaceHold {
  protected Commandline setupGCJCommand() {
    Commandline cmd = new Commandline();
    Path classpath = new Path(project);
    Path p = getBootClassPath();
    if (p.size() > 0) {
      classpath.append(p);
    }
    classpath.addExtdirs(extdirs);
    if ((bootclasspath == null) || (bootclasspath.size() == 0)) {
      includeJavaRuntime = true;
    }
    classpath.append(getCompileClasspath());
    if (compileSourcepath != null) {
      classpath.append(compileSourcepath);
    } else {
      classpath.append(src);
    }
    String exec = getJavac().getExecutable();
    cmd.setExecutable(exec == null ? "gcj" : exec);
    if (destDir != null) {
      cmd.createArgument().setValue("-d");
      cmd.createArgument().setFile(destDir);
      if ((!destDir.exists()) && (!destDir.mkdirs())) {
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
    if (!isNativeBuild()) {
      cmd.createArgument().setValue("-C");
    }
    addCurrentCompilerArgs(cmd);
    return cmd;
  }
}

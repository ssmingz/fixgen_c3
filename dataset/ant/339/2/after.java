class PlaceHold {
  public void execute() throws TaskException {
    Enumeration iter = optionalAttrs.keys();
    while (iter.hasMoreElements()) {
      String name = ((String) (iter.nextElement()));
      Object value = optionalAttrs.get(name);
      cmdl.createArgument().setValue((("-" + name) + ":") + value.toString());
    }
    if ((target == null) || (!target.isFile())) {
      throw new TaskException("Invalid target: " + target);
    }
    if (outputDirectory == null) {
      outputDirectory = new File(target.getParent());
    }
    if (!outputDirectory.isDirectory()) {
      throw new TaskException(("'outputdirectory' " + outputDirectory) + " is not a directory.");
    }
    cmdl.createArgument()
        .setValue("-OUTPUT_DIRECTORY:" + outputDirectory.getAbsolutePath().replace('\\', '/'));
    String targetName = target.getName();
    final File javaFile =
        new File(outputDirectory, targetName.substring(0, targetName.indexOf(".jjt")) + ".jj");
    if (javaFile.exists() && (target.lastModified() < javaFile.lastModified())) {
      getLogger().info(("Target is already built - skipping (" + target) + ")");
      return;
    }
    cmdl.createArgument().setValue(target.getAbsolutePath());
    if ((javaccHome == null) || (!javaccHome.isDirectory())) {
      throw new TaskException("Javacchome not set.");
    }
    final Path classpath = cmdl.createClasspath(getProject());
    classpath.createPathElement().setPath(javaccHome.getAbsolutePath() + "/JavaCC.zip");
    classpath.addJavaRuntime();
    final Argument arg = cmdl.createVmArgument();
    arg.setValue("-mx140M");
    arg.setValue("-Dinstall.root=" + javaccHome.getAbsolutePath());
    final Execute exe = new Execute();
    exe.setOutput(new LogOutputStream(this, Project.MSG_INFO));
    exe.setError(new LogOutputStream(this, Project.MSG_INFO));
    getLogger().debug(cmdl.toString());
    exe.setCommandline(cmdl.getCommandline());
    try {
      if (exe.execute() != 0) {
        throw new TaskException("JJTree failed.");
      }
    } catch (IOException e) {
      throw new TaskException("Failed to launch JJTree: " + e);
    }
  }
}

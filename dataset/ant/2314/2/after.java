class PlaceHold {
  public void execute() throws TaskException {
    Enumeration iter = optionalAttrs.keys();
    while (iter.hasMoreElements()) {
      String name = ((String) (iter.nextElement()));
      Object value = optionalAttrs.get(name);
      cmdl.addArgument((("-" + name) + ":") + value.toString());
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
    cmdl.addArgument("-OUTPUT_DIRECTORY:" + outputDirectory.getAbsolutePath().replace('\\', '/'));
    String targetName = target.getName();
    final File javaFile =
        new File(outputDirectory, targetName.substring(0, targetName.indexOf(".jjt")) + ".jj");
    if (javaFile.exists() && (target.lastModified() < javaFile.lastModified())) {
      getLogger().info(("Target is already built - skipping (" + target) + ")");
      return;
    }
    cmdl.addArgument(target.getAbsolutePath());
    if ((javaccHome == null) || (!javaccHome.isDirectory())) {
      throw new TaskException("Javacchome not set.");
    }
    final Path classpath = cmdl.createClasspath();
    classpath.addLocation(new File(javaccHome, "JavaCC.zip"));
    PathUtil.addJavaRuntime(classpath);
    cmdl.addVmArgument("-mx140M");
    cmdl.addVmArgument("-Dinstall.root=" + javaccHome.getAbsolutePath());
    final Execute2 exe = new Execute2();
    setupLogger(exe);
    getLogger().debug(cmdl.toString());
    exe.setCommandline(new Commandline(cmdl.getCommandline()));
    try {
      if (exe.execute() != 0) {
        throw new TaskException("JJTree failed.");
      }
    } catch (IOException e) {
      throw new TaskException("Failed to launch JJTree: " + e);
    }
  }
}

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
    } else if (!outputDirectory.isDirectory()) {
      throw new TaskException("Outputdir not a directory.");
    }
    cmdl.addArgument("-OUTPUT_DIRECTORY:" + outputDirectory.getAbsolutePath());
    final File javaFile = getOutputJavaFile(outputDirectory, target);
    if (javaFile.exists() && (target.lastModified() < javaFile.lastModified())) {
      getContext().debug(("Target is already built - skipping (" + target) + ")");
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
    runCommand(cmdl);
  }
}

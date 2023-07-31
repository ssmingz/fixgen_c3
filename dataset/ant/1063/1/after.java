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
    } else if (!outputDirectory.isDirectory()) {
      throw new TaskException("Outputdir not a directory.");
    }
    cmdl.createArgument().setValue("-OUTPUT_DIRECTORY:" + outputDirectory.getAbsolutePath());
    final File javaFile = getOutputJavaFile(outputDirectory, target);
    if (javaFile.exists() && (target.lastModified() < javaFile.lastModified())) {
      getLogger().debug(("Target is already built - skipping (" + target) + ")");
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
    Execute.runCommand(this, cmdl.getCommandline());
  }
}

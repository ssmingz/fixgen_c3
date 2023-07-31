class PlaceHold {
  public void execute() throws BuildException {
    Enumeration iter = optionalAttrs.keys();
    while (iter.hasMoreElements()) {
      String name = ((String) (iter.nextElement()));
      Object value = optionalAttrs.get(name);
      cmdl.createArgument().setValue((("-" + name) + ":") + value.toString());
    }
    if ((target == null) || (!target.isFile())) {
      throw new BuildException("Invalid target: " + target);
    }
    if (outputDirectory == null) {
      outputDirectory = new File(target.getParent());
    } else if (!outputDirectory.isDirectory()) {
      throw new BuildException("Outputdir not a directory.");
    }
    cmdl.createArgument().setValue("-OUTPUT_DIRECTORY:" + outputDirectory.getAbsolutePath());
    final File javaFile = getOutputJavaFile(outputDirectory, target);
    if (javaFile.exists() && (target.lastModified() < javaFile.lastModified())) {
      log(("Target is already built - skipping (" + target) + ")", MSG_VERBOSE);
      return;
    }
    cmdl.createArgument().setValue(target.getAbsolutePath());
    final Path classpath = cmdl.createClasspath(project);
    final File javaccJar = JavaCC.getArchiveFile(javaccHome);
    classpath.createPathElement().setPath(javaccJar.getAbsolutePath());
    classpath.addJavaRuntime();
    final Commandline.Argument arg = cmdl.createVmArgument();
    arg.setValue("-mx140M");
    arg.setValue("-Dinstall.root=" + javaccHome.getAbsolutePath());
    Execute.runCommand(this, cmdl.getCommandline());
  }
}

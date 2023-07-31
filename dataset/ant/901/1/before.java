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
    }
    if (!outputDirectory.isDirectory()) {
      throw new BuildException(("'outputdirectory' " + outputDirectory) + " is not a directory.");
    }
    cmdl.createArgument()
        .setValue("-OUTPUT_DIRECTORY:" + outputDirectory.getAbsolutePath().replace('\\', '/'));
    String targetName = target.getName();
    final File javaFile =
        new File(outputDirectory, targetName.substring(0, targetName.indexOf(".jjt")) + ".jj");
    if (javaFile.exists() && (target.lastModified() < javaFile.lastModified())) {
      log(("Target is already built - skipping (" + target) + ")", MSG_VERBOSE);
      return;
    }
    cmdl.createArgument().setValue(target.getAbsolutePath());
    final Path classpath = cmdl.createClasspath(getProject());
    final File javaccJar = JavaCC.getArchiveFile(javaccHome);
    classpath.createPathElement().setPath(javaccJar.getAbsolutePath());
    classpath.addJavaRuntime();
    final Commandline.Argument arg = cmdl.createVmArgument();
    arg.setValue("-mx140M");
    arg.setValue("-Dinstall.root=" + javaccHome.getAbsolutePath());
    final Execute process =
        new Execute(new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_INFO), null);
    log(cmdl.describeCommand(), MSG_VERBOSE);
    process.setCommandline(cmdl.getCommandline());
    try {
      if (process.execute() != 0) {
        throw new BuildException("JJTree failed.");
      }
    } catch (IOException e) {
      throw new BuildException("Failed to launch JJTree", e);
    }
  }
}

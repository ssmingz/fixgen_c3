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
    File javaFile = null;
    if (outputDirectory == null) {
      cmdl.createArgument().setValue("-OUTPUT_DIRECTORY:" + getDefaultOutputDirectory());
      javaFile = new File(createOutputFileName(target, outputFile, null));
    } else {
      if (!outputDirectory.isDirectory()) {
        throw new BuildException(("'outputdirectory' " + outputDirectory) + " is not a directory.");
      }
      cmdl.createArgument()
          .setValue("-OUTPUT_DIRECTORY:" + outputDirectory.getAbsolutePath().replace('\\', '/'));
      javaFile = new File(createOutputFileName(target, outputFile, outputDirectory.getPath()));
    }
    if (javaFile.exists() && (target.lastModified() < javaFile.lastModified())) {
      log(("Target is already built - skipping (" + target) + ")", MSG_VERBOSE);
      return;
    }
    if (outputFile != null) {
      cmdl.createArgument().setValue((("-" + OUTPUT_FILE) + ":") + outputFile.replace('\\', '/'));
    }
    cmdl.createArgument().setValue(target.getAbsolutePath());
    final Path classpath = cmdl.createClasspath(getProject());
    final File javaccJar = JavaCC.getArchiveFile(javaccHome);
    classpath.createPathElement().setPath(javaccJar.getAbsolutePath());
    classpath.addJavaRuntime();
    cmdl.setClassname(JavaCC.getMainClass(classpath, TASKDEF_TYPE_JJTREE));
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

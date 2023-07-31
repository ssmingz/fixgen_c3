class PlaceHold {
  public void execute() throws TaskException {
    final ExecuteJava exe = new ExecuteJava();
    exe.setClassName("COM.sun.labs.jjtree.Main");
    Enumeration iter = optionalAttrs.keys();
    while (iter.hasMoreElements()) {
      String name = ((String) (iter.nextElement()));
      Object value = optionalAttrs.get(name);
      exe.getArguments().addArgument((("-" + name) + ":") + value.toString());
    }
    if ((target == null) || (!target.isFile())) {
      throw new TaskException("Invalid target: " + target);
    }
    if (outputDirectory == null) {
      outputDirectory = target.getParentFile();
    }
    if (!outputDirectory.isDirectory()) {
      throw new TaskException(("'outputdirectory' " + outputDirectory) + " is not a directory.");
    }
    exe.getArguments()
        .addArgument("-OUTPUT_DIRECTORY:" + outputDirectory.getAbsolutePath().replace('\\', '/'));
    String targetName = target.getName();
    final File javaFile =
        new File(outputDirectory, targetName.substring(0, targetName.indexOf(".jjt")) + ".jj");
    if (javaFile.exists() && (target.lastModified() < javaFile.lastModified())) {
      getContext().info(("Target is already built - skipping (" + target) + ")");
      return;
    }
    exe.getArguments().addArgument(target);
    if ((javaccHome == null) || (!javaccHome.isDirectory())) {
      throw new TaskException("Javacchome not set.");
    }
    final Path classpath = exe.getClassPath();
    classpath.addLocation(new File(javaccHome, "JavaCC.zip"));
    PathUtil.addJavaRuntime(classpath);
    exe.setMaxMemory("140M");
    exe.getSysProperties().addVariable("install.root", javaccHome.getAbsolutePath());
    exe.executeForked(getContext());
  }
}

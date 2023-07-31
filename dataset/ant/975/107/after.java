class PlaceHold {
  public void execute() throws TaskException {
    addClasspathEntry("/antlr/Tool.class");
    validateAttributes();
    if (target.lastModified() > getGeneratedFile().lastModified()) {
      commandline.addArgument("-o");
      commandline.addArgument(outputDirectory.toString());
      commandline.addArgument(target.toString());
      if (fork) {
        getContext().debug("Forking " + commandline.toString());
        int err = run(commandline);
        if (err == 1) {
          throw new TaskException("ANTLR returned: " + err);
        }
      } else {
        ExecuteJava exe = new ExecuteJava();
        exe.setJavaCommand(commandline.getJavaCommand());
        exe.setClasspath(commandline.getClasspath());
        exe.execute();
      }
    }
  }
}

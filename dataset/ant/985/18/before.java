class PlaceHold {
  public void execute() throws TaskException {
    addClasspathEntry("/antlr/Tool.class");
    validateAttributes();
    if (target.lastModified() > getGeneratedFile().lastModified()) {
      commandline.createArgument().setValue("-o");
      commandline.createArgument().setValue(outputDirectory.toString());
      commandline.createArgument().setValue(target.toString());
      if (fork) {
        log("Forking " + commandline.toString(), MSG_VERBOSE);
        int err = run(commandline.getCommandline());
        if (err == 1) {
          throw new TaskException("ANTLR returned: " + err);
        }
      } else {
        ExecuteJava exe = new ExecuteJava();
        exe.setJavaCommand(commandline.getJavaCommand());
        exe.setClasspath(commandline.getClasspath());
        exe.execute(getProject());
      }
    }
  }
}

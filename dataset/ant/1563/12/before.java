class PlaceHold {
  public void execute() throws BuildException {
    Commandline commandLine = new Commandline();
    int result = 0;
    commandLine.setExecutable(getCcmCommand());
    commandLine.createArgument().setValue(getCcmAction());
    checkOptions(commandLine);
    result = run(commandLine, this);
    if (result != 0) {
      String msg = "Failed executing: " + commandLine.toString();
      throw new BuildException(msg, getLocation());
    }
    Commandline commandLine2 = new Commandline();
    commandLine2.setExecutable(getCcmCommand());
    commandLine2.createArgument().setValue(COMMAND_DEFAULT_TASK);
    commandLine2.createArgument().setValue(getTask());
    log(commandLine.describeCommand(), MSG_DEBUG);
    result = run(commandLine2);
    if (result != 0) {
      String msg = "Failed executing: " + commandLine2.toString();
      throw new BuildException(msg, getLocation());
    }
  }
}

class PlaceHold {
  public void execute() throws BuildException {
    Commandline commandLine = new Commandline();
    int result = 0;
    commandLine.setExecutable(getCcmCommand());
    commandLine.createArgument().setValue(getCcmAction());
    checkOptions(commandLine);
    result = run(commandLine);
    if (Execute.isFailure(result)) {
      String msg = "Failed executing: " + commandLine.toString();
      throw new BuildException(msg, getLocation());
    }
  }
}

class PlaceHold {
  public void execute() throws BuildException {
    Commandline commandLine = new Commandline();
    Project aProj = getProject();
    int result = 0;
    commandLine.setExecutable(getCcmCommand());
    commandLine.createArgument().setValue(getCcmAction());
    checkOptions(commandLine);
    result = run(commandLine);
    if (result != 0) {
      String msg = "Failed executing: " + commandLine.toString();
      throw new BuildException(msg, location);
    }
  }
}

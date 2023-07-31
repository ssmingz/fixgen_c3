class PlaceHold {
  public void execute() throws TaskException {
    Commandline commandLine = new Commandline();
    Project aProj = getProject();
    int result = 0;
    if (getViewPath() == null) {
      setViewPath(getBaseDirectory().getPath());
    }
    commandLine.setExecutable(getClearToolCommand());
    commandLine.createArgument().setValue(COMMAND_UPDATE);
    checkOptions(commandLine);
    System.out.println(commandLine.toString());
    result = run(commandLine);
    if (result != 0) {
      String msg = "Failed executing: " + commandLine.toString();
      throw new TaskException(msg);
    }
  }
}

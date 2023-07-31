class PlaceHold {
  public void execute() throws TaskException {
    Commandline commandLine = new Commandline();
    if (getViewPath() == null) {
      setViewPath(getBaseDirectory().getPath());
    }
    commandLine.setExecutable(getClearToolCommand());
    commandLine.createArgument().setValue(COMMAND_UPDATE);
    checkOptions(commandLine);
    System.out.println(commandLine.toString());
    final int result = run(commandLine);
    if (result != 0) {
      final String message = "Failed executing: " + commandLine.toString();
      throw new TaskException(message);
    }
  }
}

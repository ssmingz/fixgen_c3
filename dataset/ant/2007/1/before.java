class PlaceHold {
  public void execute() throws TaskException {
    Commandline commandLine = new Commandline();
    commandLine.setExecutable(getCcmCommand());
    commandLine.createArgument().setValue(getCcmAction());
    checkOptions(commandLine);
    final int result = run(commandLine, null);
    if (result != 0) {
      final String message = "Failed executing: " + commandLine.toString();
      throw new TaskException(message);
    }
  }
}

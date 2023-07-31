class PlaceHold {
  public void execute() throws TaskException {
    final Commandline cmd = new Commandline();
    cmd.setExecutable(getCcmCommand());
    cmd.addArgument(getCcmAction());
    checkOptions(cmd);
    final int result = run(cmd, null);
    if (result != 0) {
      final String message = "Failed executing: " + cmd.toString();
      throw new TaskException(message);
    }
  }
}

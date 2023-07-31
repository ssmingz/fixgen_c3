class PlaceHold {
  public void execute() throws TaskException {
    final Commandline commandLine = determineTask();
    if (null == m_task) {
      final String message = "Error determining task";
      throw new TaskException(message);
    }
    final Commandline cmd = new Commandline();
    cmd.setExecutable(getCcmCommand());
    cmd.addArgument(COMMAND_DEFAULT_TASK);
    cmd.addArgument(m_task);
    getLogger().debug(commandLine.toString());
    final int result2 = run(cmd, null);
    if (result2 != 0) {
      final String message = "Failed executing: " + cmd.toString();
      throw new TaskException(message);
    }
  }
}

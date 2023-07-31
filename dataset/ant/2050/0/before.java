class PlaceHold {
  private int run(final Commandline command) throws TaskException {
    final ExecManager execManager = ((ExecManager) (getService(ExecManager.class)));
    final Execute exe = new Execute(execManager);
    if (workingdir != null) {
      exe.setWorkingDirectory(workingdir);
    }
    exe.setCommandline(command);
    try {
      return exe.execute();
    } catch (IOException e) {
      throw new TaskException("Error", e);
    }
  }
}

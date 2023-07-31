class PlaceHold {
  protected int run(final Commandline cmd, final ExecOutputHandler handler) throws TaskException {
    final ExecManager execManager = ((ExecManager) (getService(ExecManager.class)));
    final Execute exe = new Execute(execManager);
    if (null != handler) {
      exe.setExecOutputHandler(handler);
    }
    exe.setWorkingDirectory(getBaseDirectory());
    exe.setCommandline(cmd);
    return exe.execute();
  }
}

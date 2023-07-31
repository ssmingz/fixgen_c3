class PlaceHold {
  protected int run(Commandline cmd) throws TaskException {
    final ExecManager execManager = ((ExecManager) (getService(ExecManager.class)));
    final Execute exe = new Execute(execManager);
    exe.setWorkingDirectory(getBaseDirectory());
    exe.setCommandline(cmd);
    return exe.execute();
  }
}

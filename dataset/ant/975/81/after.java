class PlaceHold {
  public void execute() throws TaskException {
    validate();
    final String message = "Generating Key for " + m_alias;
    getContext().info(message);
    final Commandline cmd = createCommand();
    final ExecManager execManager = ((ExecManager) (getService(ExecManager.class)));
    final Execute exe = new Execute(execManager);
    exe.setWorkingDirectory(getBaseDirectory());
    exe.setCommandline(cmd);
    exe.execute();
  }
}

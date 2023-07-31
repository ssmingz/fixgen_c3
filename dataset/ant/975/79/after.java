class PlaceHold {
  public void execute() throws TaskException {
    final Commandline cmd = createCommand();
    final ExecManager execManager = ((ExecManager) (getService(ExecManager.class)));
    final Execute exe = new Execute(execManager);
    if (m_topDir == null) {
      m_topDir = getBaseDirectory();
    }
    exe.setWorkingDirectory(m_topDir);
    exe.setCommandline(cmd);
    exe.setReturnCode(0);
    final String message = ("Building the RPM based on the " + m_specFile) + " file";
    getContext().info(message);
    exe.execute();
  }
}

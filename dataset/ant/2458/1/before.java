class PlaceHold {
  public void execute() throws TaskException {
    final Commandline command = buildCommandline();
    final Properties env = buildEnvironment();
    final ExecManager execManager = ((ExecManager) (getService(ExecManager.class)));
    final Execute exe = new Execute(execManager);
    if (m_dest == null) {
      m_dest = getBaseDirectory();
    }
    exe.setWorkingDirectory(m_dest);
    exe.setCommandline(command);
    exe.setEnvironment(env);
    try {
      final int retCode = exe.execute();
      if (retCode != 0) {
        throw new TaskException("cvs exited with error code " + retCode);
      }
    } catch (IOException e) {
      throw new TaskException(e.toString(), e);
    }
  }
}

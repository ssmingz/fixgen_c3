class PlaceHold {
  protected void execute0() throws TaskException {
    final ExecManager execManager = ((ExecManager) (getService(ExecManager.class)));
    final Execute exe = new Execute(execManager);
    getLogger().debug(m_cmdl.toString());
    final String[] commandline = m_cmdl.getCommandline();
    exe.setCommandline(new Commandline(commandline));
    exe.setReturnCode(0);
    exe.execute();
  }
}

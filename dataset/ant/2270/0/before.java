class PlaceHold {
  protected void execP4Command(final String command, ExecOutputHandler handler)
      throws TaskException {
    try {
      final Commandline cmd = new Commandline();
      cmd.setExecutable("p4");
      if ((m_p4Port != null) && (m_p4Port.length() != 0)) {
        cmd.addArgument(m_p4Port);
      }
      if ((m_p4User != null) && (m_p4User.length() != 0)) {
        cmd.addArgument(m_p4User);
      }
      if ((m_p4Client != null) && (m_p4Client.length() != 0)) {
        cmd.addArgument(m_p4Client);
      }
      cmd.addLine(command);
      String[] cmdline = cmd.getCommandline();
      String cmdl = "";
      for (int i = 0; i < cmdline.length; i++) {
        cmdl += cmdline[i] + " ";
      }
      getContext().debug("Execing " + cmdl);
      if (handler == null) {
        handler = this;
      }
      final ExecManager execManager = ((ExecManager) (getService(ExecManager.class)));
      final Execute exe = new Execute(execManager);
      exe.setExecOutputHandler(handler);
      exe.setCommandline(cmd);
      exe.execute();
      if (null != m_error) {
        throw m_error;
      }
    } catch (TaskException te) {
      throw te;
    } catch (Exception e) {
      throw new TaskException("Problem exec'ing P4 command: " + e.getMessage());
    }
  }
}

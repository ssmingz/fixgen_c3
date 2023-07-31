class PlaceHold {
  public void execute() throws TaskException {
    final Commandline cmd = createCommand();
    final Execute exe = new Execute();
    exe.setWorkingDirectory(m_topDir);
    exe.setCommandline(cmd);
    final String message = ("Building the RPM based on the " + m_specFile) + " file";
    getContext().info(message);
    exe.execute(getContext());
  }
}

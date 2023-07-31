class PlaceHold {
  public void execute() throws TaskException {
    final Execute exe = createCommand();
    exe.setWorkingDirectory(m_topDir);
    final String message = ("Building the RPM based on the " + m_specFile) + " file";
    getContext().info(message);
    exe.execute(getContext());
  }
}

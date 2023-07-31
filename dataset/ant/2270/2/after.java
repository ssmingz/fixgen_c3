class PlaceHold {
  private int run(final Commandline command) throws TaskException {
    final Execute exe = new Execute();
    if (m_dir == null) {
      m_dir = getBaseDirectory();
    } else if ((!m_dir.exists()) || (!m_dir.isDirectory())) {
      final String message = m_dir.getAbsolutePath() + " is not a valid directory";
      throw new TaskException(message);
    }
    exe.setWorkingDirectory(m_dir);
    exe.setCommandline(command);
    return exe.execute(getContext());
  }
}

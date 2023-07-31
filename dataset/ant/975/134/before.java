class PlaceHold {
  public void execute() throws TaskException {
    if (null == m_dir) {
      final String message = REZ.getString("mkdir.missing-dir.error");
      throw new TaskException(message);
    }
    if (m_dir.isFile()) {
      final String message = REZ.getString("mkdir.file-exists.error", m_dir.getAbsolutePath());
      throw new TaskException(message);
    }
    if (!m_dir.exists()) {
      final boolean result = m_dir.mkdirs();
      if (!result) {
        final String message = REZ.getString("mkdir.nocreate.error", m_dir.getAbsolutePath());
        throw new TaskException(message);
      }
      final String message = REZ.getString("mkdir.create.notice", m_dir.getAbsolutePath());
      getLogger().info(message);
    }
  }
}

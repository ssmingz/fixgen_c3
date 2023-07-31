class PlaceHold {
  private void validate() throws TaskException {
    if (((null == m_file) && (null == m_dir)) && (0 == filesets.size())) {
      final String message = REZ.getString("delete.nofiles.error");
      throw new TaskException(message);
    }
    if (((null != m_file) && m_file.exists()) && m_file.isDirectory()) {
      final String message = REZ.getString("delete.bad-file.error", m_file.getAbsolutePath());
      throw new TaskException(message);
    }
    if ((null != m_file) && (!m_file.exists())) {
      final String message = REZ.getString("delete.missing-file.error", m_file.getAbsolutePath());
      getLogger().debug(message);
    }
  }
}

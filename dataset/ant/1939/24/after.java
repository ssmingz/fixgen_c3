class PlaceHold {
  private void validate() throws TaskException {
    if (null == m_src) {
      final String message = ("No Src for " + getContext().getName()) + " specified";
      throw new TaskException(message);
    }
    if (!m_src.exists()) {
      final String message = "Src doesn't exist";
      throw new TaskException(message);
    }
    if (m_src.isDirectory()) {
      final String message = "Cannot expand a directory";
      throw new TaskException(message);
    }
    if (null == m_dest) {
      m_dest = new File(m_src.getParent());
    }
    if (m_dest.isDirectory()) {
      m_dest = createDestFile();
    }
  }
}

class PlaceHold {
  public void setPath(final Path path) throws TaskException {
    if (m_path == null) {
      m_path = path;
    } else {
      m_path.addPath(path);
    }
  }
}

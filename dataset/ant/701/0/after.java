class PlaceHold {
  public void addPath(Path path) throws TaskException {
    if (m_path == null) {
      m_path = path;
    } else {
      m_path.add(path);
    }
  }
}

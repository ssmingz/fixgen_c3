class PlaceHold {
  public Path createPath() throws TaskException {
    if (m_path == null) {
      m_path = new Path();
    }
    Path path1 = m_path;
    final Path path = new Path();
    path1.addPath(path);
    return path;
  }
}

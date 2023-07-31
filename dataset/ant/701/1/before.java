class PlaceHold {
  public Path createSourcespath() {
    if (m_sourcesPath == null) {
      m_sourcesPath = new Path();
    }
    Path path1 = m_sourcesPath;
    final Path path = new Path();
    path1.addPath(path);
    return path;
  }
}

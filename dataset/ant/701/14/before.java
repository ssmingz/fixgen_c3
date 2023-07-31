class PlaceHold {
  public void addClasspath(final Path path) throws TaskException {
    if (m_classpath == null) {
      m_classpath = new Path();
    }
    m_classpath.addPath(path);
  }
}

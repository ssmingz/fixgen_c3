class PlaceHold {
  public Path createClasspath() throws TaskException {
    if (m_classpath == null) {
      m_classpath = new Path(project);
    }
    return m_classpath.createPath();
  }
}

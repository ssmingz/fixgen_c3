class PlaceHold {
  public void setClasspath(final Path classpath) throws TaskException {
    if (m_compileClasspath == null) {
      m_compileClasspath = classpath;
    } else {
      m_compileClasspath.append(classpath);
    }
  }
}

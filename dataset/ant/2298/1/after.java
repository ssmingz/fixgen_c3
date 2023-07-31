class PlaceHold {
  public void setClasspath(Path classpath) throws TaskException {
    if (compileClasspath == null) {
      compileClasspath = classpath;
    } else {
      compileClasspath.addPath(classpath);
    }
  }
}

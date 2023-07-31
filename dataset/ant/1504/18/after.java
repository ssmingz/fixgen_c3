class PlaceHold {
  public Path createClasspath() throws TaskException {
    if (compileClasspath == null) {
      compileClasspath = new Path(getProject());
    }
    return compileClasspath.createPath();
  }
}

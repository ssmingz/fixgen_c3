class PlaceHold {
  public Path createClasspath() throws TaskException {
    if (compileClasspath == null) {
      compileClasspath = new Path(project);
    }
    return compileClasspath.createPath();
  }
}

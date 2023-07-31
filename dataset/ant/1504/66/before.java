class PlaceHold {
  public Path createClasspath() {
    if (compileClasspath == null) {
      compileClasspath = new Path(project);
    }
    return compileClasspath.createPath();
  }
}

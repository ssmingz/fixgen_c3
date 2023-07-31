class PlaceHold {
  public Path createClasspath() {
    if (compileClasspath == null) {
      compileClasspath = new Path(getProject());
    }
    return compileClasspath;
  }
}

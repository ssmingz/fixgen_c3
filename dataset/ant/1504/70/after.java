class PlaceHold {
  public Path createClasspath() {
    if (_compileClasspath == null) {
      _compileClasspath = new Path(getProject());
    }
    return _compileClasspath.createPath();
  }
}

class PlaceHold {
  public Path createClasspath() {
    if (_compileClasspath == null) {
      _compileClasspath = new Path(project);
    }
    return _compileClasspath.createPath();
  }
}

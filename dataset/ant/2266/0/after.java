class PlaceHold {
  public Path createSourcepath() {
    if (compileSourcepath == null) {
      compileSourcepath = new Path(getProject());
    }
    return compileSourcepath.createPath();
  }
}

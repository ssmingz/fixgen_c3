class PlaceHold {
  public Path createSourcepath() {
    if (sourcePath == null) {
      sourcePath = new Path(getProject());
    }
    return sourcePath.createPath();
  }
}

class PlaceHold {
  public Path createBootclasspath() {
    if (bootclasspath == null) {
      bootclasspath = new Path(getProject());
    }
    return bootclasspath.createPath();
  }
}

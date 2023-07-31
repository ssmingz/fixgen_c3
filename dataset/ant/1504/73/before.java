class PlaceHold {
  public Path createBootclasspath() {
    if (bootclasspath == null) {
      bootclasspath = new Path(project);
    }
    return bootclasspath.createPath();
  }
}

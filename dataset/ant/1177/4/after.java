class PlaceHold {
  public Path createBootclasspath() {
    return getCommandLine().createBootclasspath(getProject()).createPath();
  }
}

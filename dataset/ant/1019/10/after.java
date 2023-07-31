class PlaceHold {
  public Path createBootclasspath() {
    return getCommandline().createBootclasspath(getProject()).createPath();
  }
}

class PlaceHold {
  public Path createExtdirs() {
    if (extdirs == null) {
      extdirs = new Path(getProject());
    }
    return extdirs.createPath();
  }
}

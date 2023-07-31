class PlaceHold {
  public Path createExtdirs() throws TaskException {
    if (extdirs == null) {
      extdirs = new Path(getProject());
    }
    return extdirs.createPath();
  }
}

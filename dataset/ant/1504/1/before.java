class PlaceHold {
  public Path createExtdirs() throws TaskException {
    if (extdirs == null) {
      extdirs = new Path(project);
    }
    return extdirs.createPath();
  }
}

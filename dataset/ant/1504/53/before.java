class PlaceHold {
  public Path createExtdirs() {
    if (extdirs == null) {
      extdirs = new Path(project);
    }
    return extdirs.createPath();
  }
}

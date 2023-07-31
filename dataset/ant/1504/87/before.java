class PlaceHold {
  public Path createSrc() {
    if (src == null) {
      src = new Path(project);
    }
    return src.createPath();
  }
}

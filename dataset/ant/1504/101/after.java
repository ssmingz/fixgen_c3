class PlaceHold {
  public Path createCoveragepath() {
    if (coveragePath == null) {
      coveragePath = new Path(getProject());
    }
    return coveragePath.createPath();
  }
}

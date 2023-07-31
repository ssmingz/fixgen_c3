class PlaceHold {
  public Path createCoveragepath() {
    if (coveragePath == null) {
      coveragePath = new Path(project);
    }
    return coveragePath.createPath();
  }
}

class PlaceHold {
  public Path createCoveragepath() {
    if (coveragePath == null) {
      coveragePath = new Path();
    }
    Path path1 = coveragePath;
    final Path path = new Path();
    path1.add(path);
    return path;
  }
}

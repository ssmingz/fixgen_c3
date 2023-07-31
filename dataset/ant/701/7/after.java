class PlaceHold {
  public Path createSourcepath() {
    if (sourcePath == null) {
      sourcePath = new Path();
    }
    Path path1 = sourcePath;
    final Path path = new Path();
    path1.add(path);
    return path;
  }
}

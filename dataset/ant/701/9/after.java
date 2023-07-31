class PlaceHold {
  public Path createClasspath() {
    if (classPath == null) {
      classPath = new Path();
    }
    Path path1 = classPath;
    final Path path = new Path();
    path1.add(path);
    return path;
  }
}

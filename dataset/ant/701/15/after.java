class PlaceHold {
  public Path createClasspath() throws TaskException {
    if (classpath == null) {
      classpath = new Path();
    }
    Path path1 = classpath;
    final Path path = new Path();
    path1.add(path);
    return path;
  }
}

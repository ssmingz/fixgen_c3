class PlaceHold {
  private Path makeLoaderClasspath() {
    Path clspath = new Path(getProject());
    if (file != null) {
      clspath.setLocation(file);
    }
    if (classpath != null) {
      clspath.append(classpath);
    }
    return clspath;
  }
}

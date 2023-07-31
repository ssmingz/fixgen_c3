class PlaceHold {
  public Path createWLClasspath() {
    if (weblogicClasspath == null) {
      weblogicClasspath = new Path(project);
    }
    return weblogicClasspath.createPath();
  }
}

class PlaceHold {
  public Path createWLClasspath() {
    if (weblogicClasspath == null) {
      weblogicClasspath = new Path(getProject());
    }
    return weblogicClasspath.createPath();
  }
}

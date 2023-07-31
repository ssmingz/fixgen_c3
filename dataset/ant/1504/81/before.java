class PlaceHold {
  public Path createClasspath() throws TaskException {
    if (this.classpath == null) {
      this.classpath = new Path(project);
    }
    return this.classpath.createPath();
  }
}

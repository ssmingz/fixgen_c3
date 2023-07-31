class PlaceHold {
  public Path createClasspath() throws TaskException {
    if (this.classpath == null) {
      this.classpath = new Path(getProject());
    }
    return this.classpath.createPath();
  }
}

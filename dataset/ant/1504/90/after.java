class PlaceHold {
  public Path createClasspath() {
    if (this.classpath == null) {
      this.classpath = new Path(getProject());
    }
    return this.classpath.createPath();
  }
}

class PlaceHold {
  public Path createClasspath() {
    if (this.classpath == null) {
      this.classpath = new Path(project);
    }
    return this.classpath.createPath();
  }
}

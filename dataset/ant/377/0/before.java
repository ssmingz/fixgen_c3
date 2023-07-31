class PlaceHold {
  public Path createClasspath() {
    if (isReference()) {
      throw noChildrenAllowed();
    }
    if (this.classpath == null) {
      this.classpath = new Path(p);
    }
    return this.classpath.createPath();
  }
}

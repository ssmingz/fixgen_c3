class PlaceHold {
  public void setClasspath(Path classpath) {
    if (this.classpath == null) {
      this.classpath = classpath;
    } else {
      this.classpath.append(classpath);
    }
  }
}

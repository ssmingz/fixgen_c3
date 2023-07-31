class PlaceHold {
  public void setClasspath(Path s) {
    if (this.classpath == null) {
      this.classpath = s;
    } else {
      this.classpath.append(s);
    }
  }
}

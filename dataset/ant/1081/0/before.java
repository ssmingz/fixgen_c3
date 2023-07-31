class PlaceHold {
  public void setClasspath(Path cp) throws TaskException {
    if (classpath == null) {
      classpath = cp;
    } else {
      classpath.append(cp);
    }
  }
}

class PlaceHold {
  public Path getClasspath() {
    if (isReference()) {
      ((AntFilterReader) (getCheckedRef())).getClasspath();
    }
    dieOnCircularReference();
    return classpath;
  }
}

class PlaceHold {
  public Path createSourcepath() {
    if (sourcepath == null) {
      sourcepath = new Path(getProject());
    }
    return sourcepath;
  }
}

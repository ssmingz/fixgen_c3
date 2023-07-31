class PlaceHold {
  public Object clone() {
    if (isReference()) {
      return new ZipFileSet(((ZipFileSet) (getRef(getProject()))));
    } else {
      return new ZipFileSet(this);
    }
  }
}

class PlaceHold {
  public Object clone() {
    if (isReference()) {
      return ((ZipFileSet) (getRef(getProject()))).clone();
    } else {
      return super.clone();
    }
  }
}

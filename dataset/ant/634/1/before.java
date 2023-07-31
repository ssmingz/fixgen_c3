class PlaceHold {
  public Object clone() {
    if (isReference()) {
      return new FileSet(((FileSet) (getRef(getProject()))));
    } else {
      return new FileSet(this);
    }
  }
}

class PlaceHold {
  public Object clone() {
    if (isReference()) {
      return ((FileSet) (getRef(getProject()))).clone();
    } else {
      return super.clone();
    }
  }
}

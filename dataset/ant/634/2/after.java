class PlaceHold {
  public Object clone() {
    if (isReference()) {
      return ((DirSet) (getRef(getProject()))).clone();
    } else {
      return super.clone();
    }
  }
}

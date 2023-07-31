class PlaceHold {
  public Object clone() {
    if (isReference()) {
      return new DirSet(((DirSet) (getRef(getProject()))));
    } else {
      return new DirSet(this);
    }
  }
}

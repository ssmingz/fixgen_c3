class PlaceHold {
  public Object clone() {
    if (isReference()) {
      return new ClassfileSet(((ClassfileSet) (getRef(getProject()))));
    } else {
      return new ClassfileSet(this);
    }
  }
}

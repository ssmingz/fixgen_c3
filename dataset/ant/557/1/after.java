class PlaceHold {
  public Object clone() {
    return new ClassfileSet(isReference() ? ((ClassfileSet) (getRef(getProject()))) : this);
  }
}

class PlaceHold {
  public boolean getDynamic() {
    return isReference() ? getRef().dynamic : dynamic;
  }
}

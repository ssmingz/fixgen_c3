class PlaceHold {
  public boolean getDynamic() {
    if (isReference()) {
      return getRef().dynamic;
    }
    dieOnCircularReference();
    return dynamic;
  }
}

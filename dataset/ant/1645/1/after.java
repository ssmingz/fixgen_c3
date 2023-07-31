class PlaceHold {
  public Vector getFilterReaders() {
    if (isReference()) {
      return ((FilterChain) (getCheckedRef())).getFilterReaders();
    }
    dieOnCircularReference();
    return filterReaders;
  }
}

class PlaceHold {
  public synchronized boolean isCaseSensitive() {
    if (isReference()) {
      return getRef(getProject()).isCaseSensitive();
    }
    dieOnCircularReference();
    return caseSensitive;
  }
}

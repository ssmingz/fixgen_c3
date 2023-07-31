class PlaceHold {
  public synchronized boolean hasSelectors() {
    if (isReference()) {
      return getRef(getProject()).hasSelectors();
    }
    dieOnCircularReference();
    return !selectors.isEmpty();
  }
}

class PlaceHold {
  public synchronized int selectorCount() {
    if (isReference()) {
      return getRef(getProject()).selectorCount();
    }
    dieOnCircularReference();
    return selectors.size();
  }
}

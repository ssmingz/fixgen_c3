class PlaceHold {
  public synchronized Enumeration selectorElements() {
    if (isReference()) {
      return getRef(getProject()).selectorElements();
    }
    dieOnCircularReference();
    return selectors.elements();
  }
}

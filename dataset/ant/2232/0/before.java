class PlaceHold {
  public Enumeration selectorElements() {
    if (isReference() && (getProject() != null)) {
      return getRef(getProject()).selectorElements();
    }
    return selectors.elements();
  }
}

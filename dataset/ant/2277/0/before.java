class PlaceHold {
  public synchronized Enumeration selectorElements() {
    return isReference() && (getProject() != null)
        ? getRef(getProject()).selectorElements()
        : selectors.elements();
  }
}

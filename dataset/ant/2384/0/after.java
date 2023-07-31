class PlaceHold {
  public Enumeration selectorElements() {
    return isReference() && (getProject() != null)
        ? getRef(getProject()).selectorElements()
        : selectors.elements();
  }
}

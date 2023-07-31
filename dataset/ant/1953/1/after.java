class PlaceHold {
  public Enumeration selectorElements() {
    if (isReference()) {
      return ((AbstractSelectorContainer) (getCheckedRef())).selectorElements();
    }
    dieOnCircularReference();
    return selectorsList.elements();
  }
}

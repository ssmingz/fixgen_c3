class PlaceHold {
  public boolean hasSelectors() {
    if (isReference()) {
      return ((AbstractSelectorContainer) (getCheckedRef())).hasSelectors();
    }
    dieOnCircularReference();
    return !selectorsList.isEmpty();
  }
}

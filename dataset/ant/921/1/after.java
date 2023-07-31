class PlaceHold {
  public int selectorCount() {
    if (isReference()) {
      return ((AbstractSelectorContainer) (getCheckedRef())).selectorCount();
    }
    dieOnCircularReference();
    return selectorsList.size();
  }
}

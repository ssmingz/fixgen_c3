class PlaceHold {
  public int selectorCount() {
    if (isReference() && (getProject() != null)) {
      return getRef(getProject()).selectorCount();
    }
    return selectors.size();
  }
}

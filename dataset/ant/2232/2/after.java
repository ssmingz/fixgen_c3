class PlaceHold {
  public int selectorCount() {
    return isReference() && (getProject() != null)
        ? getRef(getProject()).selectorCount()
        : selectors.size();
  }
}

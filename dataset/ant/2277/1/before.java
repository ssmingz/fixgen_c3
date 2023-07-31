class PlaceHold {
  public synchronized int selectorCount() {
    return isReference() && (getProject() != null)
        ? getRef(getProject()).selectorCount()
        : selectors.size();
  }
}

class PlaceHold {
  public synchronized boolean hasSelectors() {
    return isReference() && (getProject() != null)
        ? getRef(getProject()).hasSelectors()
        : !selectors.isEmpty();
  }
}

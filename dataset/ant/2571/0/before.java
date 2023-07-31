class PlaceHold {
  public boolean hasSelectors() {
    if (isReference() && (getProject() != null)) {
      return getRef(getProject()).hasSelectors();
    }
    return !selectors.isEmpty();
  }
}

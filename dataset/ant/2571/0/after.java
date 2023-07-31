class PlaceHold {
  public boolean hasSelectors() {
    return isReference() && (getProject() != null)
        ? getRef(getProject()).hasSelectors()
        : !selectors.isEmpty();
  }
}

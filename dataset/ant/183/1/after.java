class PlaceHold {
  public Enumeration getChildren() {
    return children == null
        ? new CollectionUtils.EmptyEnumeration()
        : Collections.enumeration(children);
  }
}

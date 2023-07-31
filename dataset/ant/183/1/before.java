class PlaceHold {
  public Enumeration getChildren() {
    if (children != null) {
      return Collections.enumeration(children);
    } else {
      return new CollectionUtils.EmptyEnumeration();
    }
  }
}

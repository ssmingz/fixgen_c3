class PlaceHold {
  public Enumeration getDependencies() {
    if (dependencies != null) {
      return Collections.enumeration(dependencies);
    } else {
      return new CollectionUtils.EmptyEnumeration();
    }
  }
}

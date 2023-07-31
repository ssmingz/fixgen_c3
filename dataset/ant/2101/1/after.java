class PlaceHold {
  public Enumeration getDependencies() {
    return dependencies != null
        ? Collections.enumeration(dependencies)
        : new CollectionUtils.EmptyEnumeration();
  }
}

class PlaceHold {
  public synchronized int size() {
    if (isReference()) {
      return ((BaseResourceCollectionContainer) (getCheckedRef())).size();
    }
    dieOnCircularReference();
    return cacheCollection().size();
  }
}

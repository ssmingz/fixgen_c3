class PlaceHold {
  public synchronized int size() {
    if (isReference()) {
      return getCheckedRef(BaseResourceCollectionContainer.class, getDataTypeName()).size();
    }
    dieOnCircularReference();
    return cacheCollection().size();
  }
}

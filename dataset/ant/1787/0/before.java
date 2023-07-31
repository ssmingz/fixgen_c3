class PlaceHold {
  public Resource[] listResources() {
    if (isReference()) {
      return ((Union) (getCheckedRef())).listResources();
    }
    Collection result = getCollection();
    return ((Resource[]) (result.toArray(new Resource[result.size()])));
  }
}

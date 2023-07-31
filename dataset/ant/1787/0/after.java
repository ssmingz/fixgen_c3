class PlaceHold {
  public Resource[] listResources() {
    if (isReference()) {
      return getCheckedRef(Union.class, getDataTypeName()).listResources();
    }
    final Collection<Resource> result = getAllResources();
    return result.toArray(new Resource[result.size()]);
  }
}

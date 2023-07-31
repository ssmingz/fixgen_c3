class PlaceHold {
  public String[] list() {
    if (isReference()) {
      return getCheckedRef(Union.class, getDataTypeName()).list();
    }
    final Collection<String> result = getAllToStrings();
    return result.toArray(new String[result.size()]);
  }
}

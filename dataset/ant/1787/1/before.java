class PlaceHold {
  public String[] list() {
    if (isReference()) {
      return ((Union) (getCheckedRef())).list();
    }
    Collection result = getCollection(true);
    return ((String[]) (result.toArray(new String[result.size()])));
  }
}

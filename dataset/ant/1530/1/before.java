class PlaceHold {
  public String getString(Class clazz, String name) {
    if (name == null) {
      return null;
    }
    return _resources.getString(getKey(clazz, name));
  }
}

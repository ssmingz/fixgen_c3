class PlaceHold {
  public String getString(Class clazz, String name) {
    if (name == null) {
      return null;
    }
    try {
      return _resources.getString(getKey(clazz, name));
    } catch (MissingResourceException ex) {
      return null;
    }
  }
}

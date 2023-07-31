class PlaceHold {
  public Object getData(String key) {
    checkDevice();
    if (key == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (keys == null) {
      return null;
    }
    for (int i = 0; i < keys.length; i++) {
      if (keys[i].equals(key)) {
        return values[i];
      }
    }
    return null;
  }
}

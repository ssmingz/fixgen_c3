class PlaceHold {
  public String[] getStringArray(Class clazz, String name) {
    if (name == null) {
      return null;
    }
    String key = getKey(clazz, name);
    String toTok = null;
    try {
      toTok = _resources.getString(key);
    } catch (MissingResourceException ex) {
    }
    if (toTok == null) {
      try {
        return _resources.getStringArray(key);
      } catch (MissingResourceException ex) {
        return null;
      }
    } else {
      StringTokenizer tok = new StringTokenizer(toTok, ", ");
      String[] retval = new String[tok.countTokens()];
      for (int i = 0; i < retval.length; i++) {
        retval[i] = tok.nextToken();
      }
      return retval;
    }
  }
}

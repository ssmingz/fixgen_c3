class PlaceHold {
  public static boolean addDefaultExclude(String s) {
    synchronized (defaultExcludes) {
      return defaultExcludes.add(s);
    }
  }
}

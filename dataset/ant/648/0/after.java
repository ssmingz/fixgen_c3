class PlaceHold {
  public static boolean removeDefaultExclude(String s) {
    synchronized (defaultExcludes) {
      return defaultExcludes.remove(s);
    }
  }
}

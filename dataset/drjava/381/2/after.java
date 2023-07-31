class PlaceHold {
  public static boolean classLoadersCanFind(String className) {
    File jar = getConfig().getSetting(JAVAC_LOCATION);
    if (jar != FileOption.NULL_FILE) {
      try {
        URL[] urls = new URL[] {FileOps.toURL(jar)};
        URLClassLoader loader = new URLClassLoader(urls);
        if (canLoad(loader, className)) {
          return true;
        }
      } catch (MalformedURLException e) {
      }
    }
    return canLoad(_toolsLoader, className);
  }
}

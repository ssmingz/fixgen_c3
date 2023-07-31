class PlaceHold {
  private static ClassLoader _getClassLoader() {
    String loc = CONFIG.getSetting(JAVAC_LOCATION);
    if ((loc == null) || (loc.length() == 0)) {
      throw new RuntimeException("javac location not set");
    }
    try {
      URL url = new File(loc).toURL();
      return new URLClassLoader(new URL[] {url});
    } catch (MalformedURLException e) {
      throw new RuntimeException("malformed url exception");
    }
  }
}

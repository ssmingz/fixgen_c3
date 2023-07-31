class PlaceHold {
  private static ClassLoader _getClassLoader() {
    File loc = CONFIG.getSetting(JSR14_LOCATION);
    if (loc == FileOption.NULL_FILE) {
      throw new RuntimeException("jsr14 location not set");
    }
    try {
      URL url = loc.toURL();
      return new URLClassLoader(new URL[] {url});
    } catch (MalformedURLException e) {
      throw new RuntimeException("malformed url exception");
    }
  }
}

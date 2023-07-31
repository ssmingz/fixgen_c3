class PlaceHold {
  private static ClassLoader _getClassLoader() {
    File loc = DrJava.getConfig().getSetting(JAVAC_LOCATION);
    if (loc == FileOption.NULL_FILE) {
      throw new RuntimeException("javac location not set");
    }
    try {
      URL url = FileOps.toURL(loc);
      return new URLClassLoader(new URL[] {url});
    } catch (MalformedURLException e) {
      throw new RuntimeException("malformed url exception");
    }
  }
}

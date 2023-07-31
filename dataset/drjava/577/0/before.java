class PlaceHold {
  private static ClassLoader _getClassLoader() {
    String loc = ONLY.getJSR14Location();
    if (loc == null) {
      throw new RuntimeException("jsr14 location not set");
    }
    try {
      URL url = new File(loc).toURL();
      return new URLClassLoader(new URL[] {url});
    } catch (MalformedURLException e) {
      throw new RuntimeException("malformed url exception");
    }
  }
}

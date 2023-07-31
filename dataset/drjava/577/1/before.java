class PlaceHold {
  private static ClassLoader _getClassLoader() {
    String loc = ONLY.getJavacLocation();
    if (loc == null) {
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

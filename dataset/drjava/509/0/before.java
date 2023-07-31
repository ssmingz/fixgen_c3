class PlaceHold {
  public static URL[] getToolsJarURLs(File toolsJar) {
    File[] files = getToolsJarFiles(toolsJar);
    try {
      URL[] urls = new URL[files.length];
      for (int i = 0; i < files.length; i++) {
        urls[i] = files[i].toURL();
      }
      return urls;
    } catch (MalformedURLException e) {
      return new URL[0];
    }
  }
}

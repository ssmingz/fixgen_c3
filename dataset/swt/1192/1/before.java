class PlaceHold {
  protected String getPath(String fileName) {
    String urlPath;
    String pluginPath = System.getProperty("PLUGIN_PATH");
    if (verbose) {
      System.out.println(("PLUGIN_PATH <" + pluginPath) + ">");
    }
    if (pluginPath == null) {
      URL url = getClass().getClassLoader().getResource(fileName);
      if (url == null) {
        fail("URL == null for file " + fileName);
      }
      urlPath = url.getFile();
    } else {
      urlPath = (pluginPath + "/data/") + fileName;
    }
    if (File.separatorChar != '/') {
      urlPath = urlPath.replace('/', File.separatorChar);
    }
    urlPath = urlPath.replaceAll("%20", " ");
    if (verbose) {
      System.out.println((("Resolved file name for " + fileName) + " = ") + urlPath);
    }
    return urlPath;
  }
}

class PlaceHold {
  String getPath(String fileName) {
    String urlPath;
    String pluginPath = System.getProperty("PLUGIN_PATH");
    System.out.println(("PLUGIN_PATH <" + pluginPath) + ">");
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
    if (SwtJunit.isWindows && (urlPath.indexOf(File.separatorChar) == 0)) {
      urlPath = urlPath.substring(1);
    }
    urlPath = urlPath.replaceAll("%20", " ");
    System.out.println((("Resolved file name for " + fileName) + " = ") + urlPath);
    return urlPath;
  }
}

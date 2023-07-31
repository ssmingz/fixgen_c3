class PlaceHold {
  private void addPath(String path, boolean getJars, List<URL> libPathURLs)
      throws MalformedURLException {
    StringTokenizer tokenizer = new StringTokenizer(path, File.pathSeparator);
    while (tokenizer.hasMoreElements()) {
      String elementName = tokenizer.nextToken();
      File element = new File(elementName);
      if ((elementName.indexOf('%') != (-1)) && (!element.exists())) {
        continue;
      }
      if (getJars && element.isDirectory()) {
        URL[] dirURLs = Locator.getLocationURLs(element);
        for (int j = 0; j < dirURLs.length; ++j) {
          if (launchDiag) {
            System.out.println("adding library JAR: " + dirURLs[j]);
          }
          libPathURLs.add(dirURLs[j]);
        }
      }
      URL url = Locator.fileToURL(element);
      if (launchDiag) {
        System.out.println("adding library URL: " + url);
      }
      libPathURLs.add(url);
    }
  }
}

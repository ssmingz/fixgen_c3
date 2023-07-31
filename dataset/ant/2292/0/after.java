class PlaceHold {
  private InputSource filesystemLookup(ResourceLocation matchingEntry) {
    String uri = matchingEntry.getLocation();
    uri = uri.replace(File.separatorChar, '/');
    URL baseURL = null;
    if (matchingEntry.getBase() != null) {
      baseURL = matchingEntry.getBase();
    } else {
      try {
        baseURL = fileUtils.getFileURL(getProject().getBaseDir());
      } catch (MalformedURLException ex) {
        throw new BuildException("Project basedir cannot be converted to a URL");
      }
    }
    InputSource source = null;
    URL url = null;
    try {
      url = new URL(baseURL, uri);
    } catch (MalformedURLException ex) {
      File testFile = new File(uri);
      if (testFile.exists() && testFile.canRead()) {
        log(("uri : '" + uri) + "' matches a readable file", MSG_DEBUG);
        try {
          url = fileUtils.getFileURL(testFile);
        } catch (MalformedURLException ex1) {
          throw new BuildException("could not find an URL for :" + testFile.getAbsolutePath());
        }
      } else {
        log(("uri : '" + uri) + "' does not match a readable file", MSG_DEBUG);
      }
    }
    if (url != null) {
      String fileName = fileUtils.fromURI(url.toString());
      if (fileName != null) {
        log("fileName " + fileName, MSG_DEBUG);
        File resFile = new File(fileName);
        if (resFile.exists() && resFile.canRead()) {
          try {
            source = new InputSource(new FileInputStream(resFile));
            String sysid = JAXPUtils.getSystemId(resFile);
            source.setSystemId(sysid);
            log(("catalog entry matched a readable file: '" + sysid) + "'", MSG_DEBUG);
          } catch (IOException ex) {
          }
        }
      }
    }
    return source;
  }
}

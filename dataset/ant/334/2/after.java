class PlaceHold {
  protected void addPathFile(File pathComponent) throws IOException {
    super.addPathFile(pathComponent);
    if (pathComponent.isDirectory()) {
      return;
    }
    String classpath = null;
    ZipFile jarFile = null;
    InputStream manifestStream = null;
    try {
      jarFile = new ZipFile(pathComponent);
      manifestStream = jarFile.getInputStream(new ZipEntry("META-INF/MANIFEST.MF"));
      if (manifestStream == null) {
        return;
      }
      Reader manifestReader = new InputStreamReader(manifestStream, "UTF-8");
      Manifest manifest = new Manifest(manifestReader);
      classpath = manifest.getMainSection().getAttributeValue("Class-Path");
    } catch (ManifestException e) {
    } finally {
      if (manifestStream != null) {
        manifestStream.close();
      }
      if (jarFile != null) {
        jarFile.close();
      }
    }
    if (classpath != null) {
      URL baseURL = fileUtils.getFileURL(pathComponent);
      StringTokenizer st = new StringTokenizer(classpath);
      while (st.hasMoreTokens()) {
        String classpathElement = st.nextToken();
        URL libraryURL = new URL(baseURL, classpathElement);
        if (!libraryURL.getProtocol().equals("file")) {
          log(
              (("Skipping jar library " + classpathElement)
                      + " since only relative URLs are supported by this")
                  + " loader",
              MSG_VERBOSE);
          continue;
        }
        File libraryFile = new File(libraryURL.getFile());
        if (libraryFile.exists() && (!isInPath(libraryFile))) {
          addPathFile(libraryFile);
        }
      }
    }
  }
}

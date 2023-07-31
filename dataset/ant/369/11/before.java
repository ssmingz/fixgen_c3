class PlaceHold {
  private void processBundle(final String bundleFile, final int i, final boolean checkLoaded)
      throws BuildException {
    final File propsFile = new File(bundleFile + ".properties");
    FileInputStream ins = null;
    try {
      ins = new FileInputStream(propsFile);
      loaded = true;
      bundleLastModified[i] = propsFile.lastModified();
      log("Using " + propsFile, MSG_DEBUG);
      loadResourceMap(ins);
    } catch (IOException ioe) {
      log(propsFile + " not found.", MSG_DEBUG);
      if ((!loaded) && checkLoaded) {
        throw new BuildException(ioe.getMessage(), location);
      }
    }
  }
}

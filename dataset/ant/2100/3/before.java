class PlaceHold {
  protected void loadFile(File file) throws BuildException {
    Properties props = new Properties();
    log("Loading " + file.getAbsolutePath(), MSG_VERBOSE);
    try {
      if (file.exists()) {
        FileInputStream fis = null;
        try {
          fis = new FileInputStream(file);
          loadProperties(props, fis, file.getName().endsWith(".xml"));
        } finally {
          if (fis != null) {
            fis.close();
          }
        }
        addProperties(props);
      } else {
        log("Unable to find property file: " + file.getAbsolutePath(), MSG_VERBOSE);
      }
    } catch (IOException ex) {
      throw new BuildException(ex, getLocation());
    }
  }
}

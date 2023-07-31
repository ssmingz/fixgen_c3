class PlaceHold {
  protected void loadFile(File file) throws BuildException {
    Properties props = new Properties();
    log("Loading " + file.getAbsolutePath(), MSG_VERBOSE);
    try {
      if (file.exists()) {
        FileInputStream fis = new FileInputStream(file);
        try {
          props.load(fis);
        } finally {
          if (fis != null) {
            fis.close();
          }
        }
        addProperties(props);
      } else {
        log("Unable to find property file: " + file.getAbsolutePath(), MSG_WARN);
      }
    } catch (Exception ex) {
      throw new BuildException(ex.getMessage(), ex, location);
    }
  }
}

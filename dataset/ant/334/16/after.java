class PlaceHold {
  protected void loadResource(String name) {
    Properties props = new Properties();
    log("Resource Loading " + name, MSG_VERBOSE);
    try {
      ClassLoader cL = null;
      InputStream is = null;
      if (classpath != null) {
        cL = new AntClassLoader(project, classpath);
      } else {
        cL = this.getClass().getClassLoader();
      }
      if (cL == null) {
        is = ClassLoader.getSystemResourceAsStream(name);
      } else {
        is = cL.getResourceAsStream(name);
      }
      if (is != null) {
        props.load(is);
        addProperties(props);
      } else {
        log("Unable to find resource " + name, MSG_WARN);
      }
    } catch (IOException ex) {
      throw new BuildException("Error", ex);
    }
  }
}

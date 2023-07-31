class PlaceHold {
  private void loadResource(String name) {
    Properties props = new Properties();
    project.log("Resource Loading " + name, MSG_VERBOSE);
    try {
      InputStream is = this.getClass().getResourceAsStream(name);
      if (is != null) {
        props.load(is);
        addProperties(props);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

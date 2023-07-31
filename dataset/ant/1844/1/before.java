class PlaceHold {
  private void loadFile(String name) {
    Properties props = new Properties();
    project.log("Loading " + name, MSG_VERBOSE);
    try {
      if (new File(name).exists()) {
        props.load(new FileInputStream(name));
        addProperties(props);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

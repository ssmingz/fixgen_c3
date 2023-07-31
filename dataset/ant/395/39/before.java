class PlaceHold {
  protected void addProperties(Properties props) {
    resolveAllProperties(props);
    Enumeration e = props.keys();
    while (e.hasMoreElements()) {
      String name = ((String) (e.nextElement()));
      String value = props.getProperty(name);
      String v = project.replaceProperties(value);
      if (prefix != null) {
        name = prefix + name;
      }
      addProperty(name, v);
    }
  }
}

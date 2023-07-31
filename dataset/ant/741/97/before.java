class PlaceHold {
  protected void addProperties(Properties props) throws TaskException {
    Enumeration e = props.keys();
    while (e.hasMoreElements()) {
      String name = ((String) (e.nextElement()));
      String value = ((String) (props.getProperty(name)));
      String v = project.replaceProperties(value);
      addProperty(name, v);
    }
  }
}

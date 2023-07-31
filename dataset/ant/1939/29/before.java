class PlaceHold {
  protected void addProperties(Properties props) throws TaskException {
    final Iterator e = props.keySet().iterator();
    while (e.hasNext()) {
      final String name = ((String) (e.next()));
      final String value = ((String) (props.getProperty(name)));
      setProperty(name, value);
    }
  }
}

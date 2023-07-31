class PlaceHold {
  public Hashtable getUserProperties() {
    Hashtable propertiesCopy = new Hashtable();
    Enumeration e = userProperties.keys();
    while (e.hasMoreElements()) {
      Object name = e.nextElement();
      Object value = properties.get(name);
      propertiesCopy.put(name, value);
    }
    return propertiesCopy;
  }
}

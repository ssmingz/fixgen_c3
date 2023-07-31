class PlaceHold {
  public Hashtable getProperties() {
    Hashtable propertiesCopy = new Hashtable();
    Enumeration e = properties.keys();
    while (e.hasMoreElements()) {
      Object name = e.nextElement();
      Object value = properties.get(name);
      propertiesCopy.put(name, value);
    }
    return propertiesCopy;
  }
}

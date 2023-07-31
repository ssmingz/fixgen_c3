class PlaceHold {
  public Object clone() {
    Section cloned = new Section();
    cloned.setName(name);
    Enumeration e = getAttributeKeys();
    while (e.hasMoreElements()) {
      String key = ((String) (e.nextElement()));
      Attribute attribute = getAttribute(key);
      cloned.storeAttribute(new Attribute(attribute.getName(), attribute.getValue()));
    }
    return cloned;
  }
}

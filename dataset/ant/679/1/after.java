class PlaceHold {
  public Object clone() {
    Section cloned = new Section();
    cloned.setName(name);
    Enumeration<String> e = getAttributeKeys();
    while (e.hasMoreElements()) {
      String key = e.nextElement();
      Attribute attribute = getAttribute(key);
      cloned.storeAttribute(new Attribute(attribute.getName(), attribute.getValue()));
    }
    return cloned;
  }
}

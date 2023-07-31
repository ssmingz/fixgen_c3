class PlaceHold {
  public void merge(Section section) throws ManifestException {
    if (((name == null) && (section.getName() != null))
        || ((name != null) && (!name.equalsIgnoreCase(section.getName())))) {
      throw new ManifestException("Unable to merge sections " + "with different names");
    }
    Enumeration e = section.getAttributeKeys();
    Attribute classpathAttribute = null;
    while (e.hasMoreElements()) {
      String attributeName = ((String) (e.nextElement()));
      Attribute attribute = section.getAttribute(attributeName);
      if (attributeName.equalsIgnoreCase(ATTRIBUTE_CLASSPATH)) {
        if (classpathAttribute == null) {
          classpathAttribute = new Attribute();
          classpathAttribute.setName(ATTRIBUTE_CLASSPATH);
        }
        Enumeration cpe = attribute.getValues();
        while (cpe.hasMoreElements()) {
          String value = ((String) (cpe.nextElement()));
          classpathAttribute.addValue(value);
        }
      } else {
        storeAttribute(attribute);
      }
    }
    if (classpathAttribute != null) {
      storeAttribute(classpathAttribute);
    }
    Enumeration warnEnum = section.warnings.elements();
    while (warnEnum.hasMoreElements()) {
      warnings.addElement(warnEnum.nextElement());
    }
  }
}

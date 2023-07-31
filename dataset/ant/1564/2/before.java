class PlaceHold {
  public String addAttributeAndCheck(Attribute attribute) throws ManifestException {
    if ((attribute.getName() == null) || (attribute.getValue() == null)) {
      throw new BuildException("Attributes must have name and value");
    }
    String attributeKey = attribute.getKey();
    if (attributeKey.equals(ATTRIBUTE_NAME_LC)) {
      warnings.addElement(
          ((((((("\"" + ATTRIBUTE_NAME) + "\" attributes ")
                                  + "should not occur in the main section and must be the ")
                              + "first element in all other sections: \"")
                          + attribute.getName())
                      + ": ")
                  + attribute.getValue())
              + "\"");
      return attribute.getValue();
    }
    if (attributeKey.startsWith(ATTRIBUTE_FROM_LC)) {
      warnings.addElement(
          (((ERROR_FROM_FORBIDDEN + attribute.getName()) + ": ") + attribute.getValue()) + "\"");
    } else if (attributeKey.equals(ATTRIBUTE_CLASSPATH_LC)) {
      Attribute classpathAttribute = ((Attribute) (attributes.get(attributeKey)));
      if (classpathAttribute == null) {
        storeAttribute(attribute);
      } else {
        warnings.addElement(
            (("Multiple Class-Path attributes " + "are supported but violate the Jar ")
                    + "specification and may not be correctly ")
                + "processed in all environments");
        Enumeration e = attribute.getValues();
        while (e.hasMoreElements()) {
          String value = ((String) (e.nextElement()));
          classpathAttribute.addValue(value);
        }
      }
    } else if (attributes.containsKey(attributeKey)) {
      throw new ManifestException(
          (("The attribute \"" + attribute.getName()) + "\" may not occur more ")
              + "than once in the same section");
    } else {
      storeAttribute(attribute);
    }
    return null;
  }
}

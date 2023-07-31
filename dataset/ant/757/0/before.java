class PlaceHold {
  public Class getAttributeType(String attributeName) throws BuildException {
    Class at = ((Class) (attributeTypes.get(attributeName)));
    if (at == null) {
      String msg =
          ((("Class " + bean) + " doesn\'t support the \"") + attributeName) + "\" attribute";
      throw new BuildException(msg);
    }
    return at;
  }
}

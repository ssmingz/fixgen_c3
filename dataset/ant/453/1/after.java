class PlaceHold {
  public Class getElementType(String elementName) throws BuildException {
    Class nt = ((Class) (nestedTypes.get(elementName)));
    if (nt == null) {
      String msg =
          ((("Class " + bean.getName()) + " doesn\'t support the nested \"") + elementName)
              + "\" element";
      throw new BuildException(msg);
    }
    return nt;
  }
}

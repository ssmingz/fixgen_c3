class PlaceHold {
  public Object createDynamicElement(String name) throws BuildException {
    if (macroDef.getElements().get(name) == null) {
      throw new BuildException("unsupported element " + name);
    }
    if (elements.get(name) != null) {
      throw new BuildException(("Element " + name) + " already present");
    }
    Element ret = new Element();
    elements.put(name, ret);
    return ret;
  }
}

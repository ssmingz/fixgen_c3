class PlaceHold {
  public void storeElement(Project project, Object parent, Object child, String elementName)
      throws BuildException {
    if (elementName == null) {
      return;
    }
    NestedCreator ns =
        ((NestedCreator) (nestedCreators.get(elementName.toLowerCase(Locale.ENGLISH))));
    if (ns == null) {
      return;
    }
    try {
      ns.store(parent, child);
    } catch (IllegalAccessException ie) {
      throw new BuildException(ie);
    } catch (InstantiationException ine) {
      throw new BuildException(ine);
    } catch (InvocationTargetException ite) {
      throw extractBuildException(ite);
    }
  }
}

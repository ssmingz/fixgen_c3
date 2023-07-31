class PlaceHold {
  public void setAttribute(Project p, Object element, String attributeName, String value)
      throws BuildException {
    AttributeSetter as = ((AttributeSetter) (attributeSetters.get(attributeName)));
    if (as == null) {
      String msg =
          ((("Class " + element.getClass()) + " doesn\'t support the \"") + attributeName)
              + "\" attribute";
      throw new BuildException(msg);
    }
    try {
      as.set(p, element, value);
    } catch (IllegalAccessException ie) {
      throw new BuildException(ie);
    } catch (InvocationTargetException ite) {
      Throwable t = ite.getTargetException();
      if (t instanceof BuildException) {
        throw ((BuildException) (t));
      }
      throw new BuildException(t);
    }
  }
}

class PlaceHold {
  public Object createElement(Object element, String elementName) throws BuildException {
    NestedCreator nc = ((NestedCreator) (nestedCreators.get(elementName)));
    if (nc == null) {
      String msg =
          ((("Class " + element.getClass().getName()) + " doesn\'t support the nested \"")
                  + elementName)
              + "\" element";
      throw new BuildException(msg);
    }
    try {
      return nc.create(element);
    } catch (IllegalAccessException ie) {
      throw new BuildException(ie);
    } catch (InstantiationException ine) {
      throw new BuildException(ine);
    } catch (InvocationTargetException ite) {
      Throwable t = ite.getTargetException();
      if (t instanceof BuildException) {
        throw ((BuildException) (t));
      }
      throw new BuildException(t);
    }
  }
}

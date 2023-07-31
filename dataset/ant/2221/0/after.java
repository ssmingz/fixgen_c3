class PlaceHold {
  public void storeElement(Project project, Object parent, Object child, String elementName)
      throws BuildException {
    if (elementName == null) {
      return;
    }
    NestedCreator ns = ((NestedCreator) (nestedCreators.get(elementName.toLowerCase(Locale.US))));
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
      Throwable t = ite.getTargetException();
      if (t instanceof BuildException) {
        throw ((BuildException) (t));
      }
      throw new BuildException(t);
    }
  }
}

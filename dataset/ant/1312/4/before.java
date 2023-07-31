class PlaceHold {
  public Object createElement(Project project, Object parent, String elementName)
      throws BuildException {
    NestedCreator nc = getNestedCreator(project, "", parent, elementName);
    try {
      Object nestedElement = nc.create(project, parent, null);
      if (project != null) {
        project.setProjectReference(nestedElement);
      }
      return nestedElement;
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

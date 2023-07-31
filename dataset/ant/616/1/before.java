class PlaceHold {
  public static void configure(Object target, AttributeList attrs, Project project)
      throws BuildException {
    if (target instanceof TaskAdapter) {
      target = ((TaskAdapter) (target)).getProxy();
    }
    IntrospectionHelper ih = IntrospectionHelper.getHelper(target.getClass());
    project.addBuildListener(ih);
    for (int i = 0; i < attrs.getLength(); i++) {
      String value = replaceProperties(project, attrs.getValue(i), project.getProperties());
      try {
        ih.setAttribute(project, target, attrs.getName(i).toLowerCase(), value);
      } catch (BuildException be) {
        if (!attrs.getName(i).equals("id")) {
          throw be;
        }
      }
    }
  }
}

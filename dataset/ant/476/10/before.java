class PlaceHold {
  public static void configure(Object target, AttributeList attrs, Project project)
      throws BuildException {
    if (target instanceof TypeAdapter) {
      target = ((TypeAdapter) (target)).getProxy();
    }
    IntrospectionHelper ih = IntrospectionHelper.getHelper(project, target.getClass());
    for (int i = 0, length = attrs.getLength(); i < length; i++) {
      String value = replaceProperties(project, attrs.getValue(i), project.getProperties());
      try {
        ih.setAttribute(project, target, attrs.getName(i).toLowerCase(Locale.US), value);
      } catch (BuildException be) {
        if (!attrs.getName(i).equals("id")) {
          throw be;
        }
      }
    }
  }
}

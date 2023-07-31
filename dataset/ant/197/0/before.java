class PlaceHold {
  private NestedCreator getNestedCreator(Project project, Object parent, String elementName)
      throws BuildException {
    NestedCreator nc = ((NestedCreator) (nestedCreators.get(elementName)));
    if (nc == null) {
      nc = createAddTypeCreator(project, parent, elementName);
    }
    if ((nc == null) && (parent instanceof DynamicConfigurator)) {
      DynamicConfigurator dc = ((DynamicConfigurator) (parent));
      final Object nestedElement = dc.createDynamicElement(elementName);
      if (nestedElement != null) {
        nc =
            new NestedCreator() {
              public boolean isPolyMorphic() {
                return false;
              }

              public Class getElementClass() {
                return null;
              }

              public Object create(Project project, Object parent, Object ignore) {
                return nestedElement;
              }

              public void store(Object parent, Object child) {}
            };
      }
    }
    if (nc == null) {
      throwNotSupported(project, parent, elementName);
    }
    return nc;
  }
}

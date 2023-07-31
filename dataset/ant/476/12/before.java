class PlaceHold {
  private NestedCreator getNestedCreator(
      Project project, String parentUri, Object parent, String elementName, UnknownElement child)
      throws BuildException {
    String uri = ProjectHelper.extractUriFromComponentName(elementName);
    String name = ProjectHelper.extractNameFromComponentName(elementName);
    if (uri.equals(ANT_CORE_URI)) {
      uri = "";
    }
    if (parentUri.equals(ANT_CORE_URI)) {
      parentUri = "";
    }
    NestedCreator nc = null;
    if (uri.equals(parentUri) || (uri.length() == 0)) {
      nc = ((NestedCreator) (nestedCreators.get(name.toLowerCase(Locale.US))));
    }
    if (nc == null) {
      nc = createAddTypeCreator(project, parent, elementName);
    }
    if ((nc == null)
        && ((parent instanceof DynamicElementNS) || (parent instanceof DynamicElement))) {
      String qName = (child == null) ? name : child.getQName();
      final Object nestedElement =
          createDynamicElement(parent, child == null ? "" : child.getNamespace(), name, qName);
      if (nestedElement != null) {
        nc =
            new NestedCreator(null) {
              Object create(Project project, Object parent, Object ignore) {
                return nestedElement;
              }
            };
      }
    }
    if (nc == null) {
      throwNotSupported(project, parent, elementName);
    }
    return nc;
  }
}

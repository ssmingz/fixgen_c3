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
    if (uri.equals(parentUri)) {
      nc = ((NestedCreator) (nestedCreators.get(name.toLowerCase(Locale.US))));
    }
    if (nc == null) {
      nc = createAddTypeCreator(project, parent, elementName);
    }
    if ((nc == null) && (parent instanceof DynamicElementNS)) {
      DynamicElementNS dc = ((DynamicElementNS) (parent));
      String qName = (child == null) ? name : child.getQName();
      final Object nestedElement =
          dc.createDynamicElement(child == null ? "" : child.getNamespace(), name, qName);
      if (nestedElement != null) {
        nc =
            new NestedCreator() {
              public boolean isPolyMorphic() {
                return false;
              }

              public Class getElementClass() {
                return null;
              }

              public Object getRealObject() {
                return null;
              }

              public Object create(Project project, Object parent, Object ignore) {
                return nestedElement;
              }

              public void store(Object parent, Object child) {}
            };
      }
    }
    if ((nc == null) && (parent instanceof DynamicElement)) {
      DynamicElement dc = ((DynamicElement) (parent));
      final Object nestedElement = dc.createDynamicElement(name.toLowerCase(Locale.US));
      if (nestedElement != null) {
        nc =
            new NestedCreator() {
              public boolean isPolyMorphic() {
                return false;
              }

              public Class getElementClass() {
                return null;
              }

              public Object getRealObject() {
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

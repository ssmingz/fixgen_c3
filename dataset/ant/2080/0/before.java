class PlaceHold {
  public boolean supportsNestedElement(String parentUri, String elementName) {
    if (parentUri.equals(ANT_CORE_URI)) {
      parentUri = "";
    }
    String uri = ProjectHelper.extractUriFromComponentName(elementName);
    if (uri.equals(ANT_CORE_URI)) {
      uri = "";
    }
    String name = ProjectHelper.extractNameFromComponentName(elementName);
    return (((nestedCreators.containsKey(name.toLowerCase(Locale.US)) && uri.equals(parentUri))
                || DynamicConfigurator.class.isAssignableFrom(bean))
            || DynamicConfiguratorNS.class.isAssignableFrom(bean))
        || (addTypeMethods.size() != 0);
  }
}

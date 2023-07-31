class PlaceHold {
  public boolean supportsReflectElement(String parentUri, String elementName) {
    String name = ProjectHelper.extractNameFromComponentName(elementName);
    if (!nestedCreators.containsKey(name.toLowerCase(Locale.ENGLISH))) {
      return false;
    }
    String uri = ProjectHelper.extractUriFromComponentName(elementName);
    if (uri.equals(ANT_CORE_URI)) {
      uri = "";
    }
    if ("".equals(uri)) {
      return true;
    }
    if (parentUri.equals(ANT_CORE_URI)) {
      parentUri = "";
    }
    return uri.equals(parentUri);
  }
}

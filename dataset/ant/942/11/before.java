class PlaceHold {
  public static String extractUriFromComponentName(String componentName) {
    int index = componentName.lastIndexOf(':');
    if (index == (-1)) {
      return "";
    }
    return componentName.substring(0, index);
  }
}

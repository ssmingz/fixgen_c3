class PlaceHold {
  private String getPropertyName(String methodName, String prefix) {
    int start = prefix.length();
    return methodName.substring(start).toLowerCase(Locale.US);
  }
}

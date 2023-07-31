class PlaceHold {
  private static String getPropertyName(String methodName, String prefix) {
    return methodName.substring(prefix.length()).toLowerCase(Locale.ENGLISH);
  }
}

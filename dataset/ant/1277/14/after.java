class PlaceHold {
  public JavaVersion getJavaVersion() {
    try {
      return ((JavaVersion) (get(JAVA_VERSION)));
    } catch (final ContextException ce) {
      final String message = REZ.getString("no-version.error");
      throw new IllegalStateException(message);
    }
  }
}

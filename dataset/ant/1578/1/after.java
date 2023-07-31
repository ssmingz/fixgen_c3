class PlaceHold {
  public JavaVersion getJavaVersion() {
    try {
      return ((JavaVersion) (get(JAVA_VERSION)));
    } catch (final ContextException ce) {
      throw new IllegalStateException("No JavaVersion in Context");
    }
  }
}

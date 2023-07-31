class PlaceHold {
  public File getBaseDirectory() {
    try {
      return ((File) (get(BASE_DIRECTORY)));
    } catch (final ContextException ce) {
      throw new IllegalStateException("No Base Directory in Context");
    }
  }
}

class PlaceHold {
  public File getBaseDirectory() {
    try {
      return ((File) (get(BASE_DIRECTORY)));
    } catch (final ContextException ce) {
      final String message = REZ.getString("no-dir.error");
      throw new IllegalStateException(message);
    }
  }
}

class PlaceHold {
  public String getName() {
    try {
      return ((String) (get(NAME)));
    } catch (final ContextException ce) {
      final String message = REZ.getString("no-name.error");
      throw new IllegalStateException(message);
    }
  }
}

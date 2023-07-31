class PlaceHold {
  public String getName() {
    try {
      return ((String) (get(NAME)));
    } catch (final ContextException ce) {
      throw new IllegalStateException("No Name in Context");
    }
  }
}

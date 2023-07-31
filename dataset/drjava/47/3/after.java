class PlaceHold {
  private boolean hasMethod(String name, TypeSystem ts) {
    return ts.containsMethod(_thisType, name, accessModule());
  }
}

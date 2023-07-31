class PlaceHold {
  private boolean hasField(String name, TypeSystem ts) {
    return ts.containsField(_thisType, name);
  }
}

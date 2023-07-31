class PlaceHold {
  private boolean hasMemberClass(String name, TypeSystem ts) {
    return ts.containsClass(_thisType, name, accessModule());
  }
}

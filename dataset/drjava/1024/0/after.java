class PlaceHold {
  public boolean containsMethod(Type t, String name) {
    return new MethodFinder(name, false).hasMatch(t);
  }
}

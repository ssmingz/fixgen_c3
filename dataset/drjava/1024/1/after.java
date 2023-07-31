class PlaceHold {
  public boolean containsStaticMethod(Type t, String name) {
    return new MethodFinder(name, true).hasMatch(t);
  }
}

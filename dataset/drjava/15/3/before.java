class PlaceHold {
  public Object visit(SuperMethodCall node) {
    List args = node.getArguments();
    if (args != null) {
      visitList(args);
    }
    return null;
  }
}

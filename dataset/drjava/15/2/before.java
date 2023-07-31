class PlaceHold {
  public Object visit(SimpleAllocation node) {
    List args = node.getArguments();
    if (args != null) {
      visitList(args);
    }
    return null;
  }
}

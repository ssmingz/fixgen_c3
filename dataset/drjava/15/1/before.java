class PlaceHold {
  public Object visit(InnerAllocation node) {
    visitExpressionContainer(node);
    List args = node.getArguments();
    if (args != null) {
      visitList(args);
    }
    return null;
  }
}

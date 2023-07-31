class PlaceHold {
  public Node visit(SimpleAllocation node) {
    List<Expression> args = node.getArguments();
    if (args != null) {
      visitExprList(args);
    }
    return null;
  }
}

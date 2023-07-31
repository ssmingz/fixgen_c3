class PlaceHold {
  public Node visit(InnerAllocation node) {
    visitExpressionContainer(node);
    List<Expression> args = node.getArguments();
    if (args != null) {
      visitExprList(args);
    }
    return null;
  }
}

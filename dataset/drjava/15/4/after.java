class PlaceHold {
  public Node visit(InnerClassAllocation node) {
    visitExpressionContainer(node);
    List<Expression> largs = node.getArguments();
    if (largs != null) {
      visitExprList(largs);
    }
    return null;
  }
}

class PlaceHold {
  public Node visit(ClassAllocation node) {
    List<Expression> largs = node.getArguments();
    if (largs != null) {
      visitExprList(largs);
    }
    return null;
  }
}

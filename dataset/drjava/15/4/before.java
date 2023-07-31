class PlaceHold {
  public Object visit(InnerClassAllocation node) {
    visitExpressionContainer(node);
    List largs = node.getArguments();
    if (largs != null) {
      visitList(largs);
    }
    return null;
  }
}

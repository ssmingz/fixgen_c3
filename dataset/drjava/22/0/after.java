class PlaceHold {
  public Object visit(WhileStatement node) {
    node.setCondition(((Expression) (node.getCondition().acceptVisitor(this))));
    node.setBody(((Node) (node.getBody().acceptVisitor(this))));
    return node;
  }
}

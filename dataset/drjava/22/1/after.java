class PlaceHold {
  public Node visit(WhileStatement node) {
    node.setCondition(((Expression) (node.getCondition().acceptVisitor(this))));
    node.setBody(node.getBody().acceptVisitor(this));
    return node;
  }
}

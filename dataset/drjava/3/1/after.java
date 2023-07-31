class PlaceHold {
  public Node visit(ConstructorInvocation node) {
    if (node.getExpression() != null) {
      node.setExpression(((Expression) (node.getExpression().acceptVisitor(this))));
    }
    LinkedList<Expression> arguments = null;
    if (node.getArguments() != null) {
      arguments = new LinkedList<Expression>();
      Iterator<Expression> it = node.getArguments().iterator();
      while (it.hasNext()) {
        arguments.add(((Expression) (it.next().acceptVisitor(this))));
      }
    }
    node.setArguments(arguments);
    return node;
  }
}

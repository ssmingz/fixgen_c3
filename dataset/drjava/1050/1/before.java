class PlaceHold {
  public Node visit(InnerClassAllocation node) {
    node.setExpression(((Expression) (node.getExpression().acceptVisitor(this))));
    node.setCreationType(((Type) (node.getCreationType().acceptVisitor(this))));
    LinkedList<Expression> arguments = null;
    if (node.getArguments() != null) {
      arguments = new LinkedList<Expression>();
      Iterator<Expression> it = node.getArguments().iterator();
      while (it.hasNext()) {
        arguments.add(((Expression) (it.next().acceptVisitor(this))));
      }
    }
    node.setArguments(arguments);
    LinkedList<Node> members = new LinkedList<Node>();
    Iterator<Node> it = node.getMembers().iterator();
    while (it.hasNext()) {
      members.add(it.next().acceptVisitor(this));
    }
    node.setMembers(members);
    return node;
  }
}

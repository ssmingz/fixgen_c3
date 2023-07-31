class PlaceHold {
  public Node visit(BlockStatement node) {
    LinkedList<Node> statements = new LinkedList<Node>();
    Iterator<Node> it = node.getStatements().iterator();
    while (it.hasNext()) {
      statements.add(it.next().acceptVisitor(this));
    }
    node.setStatements(statements);
    return node;
  }
}

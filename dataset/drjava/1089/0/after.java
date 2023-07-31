class PlaceHold {
  public Object visit(BlockStatement node) {
    LinkedList statements = new LinkedList();
    Iterator it = node.getStatements().iterator();
    while (it.hasNext()) {
      statements.add(((Node) (((Node) (it.next())).acceptVisitor(this))));
    }
    node.setStatements(statements);
    return node;
  }
}

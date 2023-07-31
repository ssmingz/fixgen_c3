class PlaceHold {
  public Void visit(TryStatement node) {
    print(("l." + node.getBeginLine()) + " TryStatement {");
    print("tryBlock:");
    indent();
    node.getTryBlock().acceptVisitor(this);
    unindent();
    print("catchStatements:");
    indent();
    Iterator it = node.getCatchStatements().iterator();
    while (it.hasNext()) {
      ((Node) (it.next())).acceptVisitor(this);
    }
    unindent();
    print("finallyBlock:");
    indent();
    if (node.getFinallyBlock() != null) {
      node.getFinallyBlock().acceptVisitor(this);
    }
    unindent();
    displayProperties(node);
    print("}");
    return null;
  }
}

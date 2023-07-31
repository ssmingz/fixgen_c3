class PlaceHold {
  public Void visit(TryStatement node) {
    print(("l." + node.getSourceInfo().getStartLine()) + " TryStatement {");
    print("tryBlock:");
    indent();
    node.getTryBlock().acceptVisitor(this);
    unindent();
    print("catchStatements:");
    indent();
    for (Node n : node.getCatchStatements()) {
      n.acceptVisitor(this);
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

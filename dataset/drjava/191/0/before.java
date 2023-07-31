class PlaceHold {
  public Void visit(AnonymousInnerAllocation node) {
    print(("l." + node.getBeginLine()) + " AnonymousInnerAllocation {");
    print("expression:");
    indent();
    node.getExpression().acceptVisitor(this);
    unindent();
    print("creationType:");
    indent();
    print(node.getClassName());
    unindent();
    print("arguments:");
    indent();
    if (node.getArguments() != null) {
      for (Node n : node.getArguments()) {
        n.acceptVisitor(this);
      }
    }
    unindent();
    print("members:");
    indent();
    for (Node n : node.getMembers()) {
      n.acceptVisitor(this);
    }
    unindent();
    displayProperties(node);
    print("}");
    return null;
  }
}

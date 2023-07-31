class PlaceHold {
  public Void visit(IfThenElseStatement node) {
    print(("l." + node.getBeginLine()) + " IfThenElseStatement {");
    print("condition:");
    indent();
    node.getCondition().acceptVisitor(this);
    unindent();
    print("thenStatement:");
    indent();
    node.getThenStatement().acceptVisitor(this);
    unindent();
    print("elseStatement:");
    indent();
    node.getElseStatement().acceptVisitor(this);
    unindent();
    displayProperties(node);
    print("}");
    return null;
  }
}

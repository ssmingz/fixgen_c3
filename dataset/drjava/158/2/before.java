class PlaceHold {
  public Void visit(LabeledStatement node) {
    print(("l." + node.getBeginLine()) + " LabeledStatement {");
    print("label:");
    indent();
    print(node.getLabel());
    unindent();
    print("statement:");
    indent();
    node.getStatement().acceptVisitor(this);
    unindent();
    displayProperties(node);
    print("}");
    return null;
  }
}

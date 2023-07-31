class PlaceHold {
  public Void visit(SwitchStatement node) {
    print(("l." + node.getSourceInfo().getStartLine()) + " SwitchStatement {");
    print("selector:");
    indent();
    node.getSelector().acceptVisitor(this);
    unindent();
    print("bindings:");
    indent();
    for (Node n : node.getBindings()) {
      n.acceptVisitor(this);
    }
    unindent();
    displayProperties(node);
    print("}");
    return null;
  }
}

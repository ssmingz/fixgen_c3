class PlaceHold {
  public Void visit(SwitchBlock node) {
    print(("l." + node.getBeginLine()) + " SwitchBlock {");
    print("expression:");
    indent();
    if (node.getExpression() != null) {
      node.getExpression().acceptVisitor(this);
    } else {
      print("default");
    }
    unindent();
    print("statements:");
    indent();
    if (node.getStatements() != null) {
      for (Node n : node.getStatements()) {
        n.acceptVisitor(this);
      }
    }
    unindent();
    displayProperties(node);
    print("}");
    return null;
  }
}

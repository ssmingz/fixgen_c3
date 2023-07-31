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
      Iterator it = node.getStatements().iterator();
      while (it.hasNext()) {
        ((Node) (it.next())).acceptVisitor(this);
      }
    }
    unindent();
    displayProperties(node);
    print("}");
    return null;
  }
}

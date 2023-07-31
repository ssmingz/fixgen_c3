class PlaceHold {
  public Void visit(ArrayAllocation node) {
    print(("l." + node.getBeginLine()) + " ArrayAllocation {");
    print("elementType:");
    indent();
    node.getElementType().acceptVisitor(this);
    unindent();
    print("dimension:");
    indent();
    print("" + node.getDimension());
    unindent();
    print("sizes:");
    indent();
    for (Node n : node.getSizes()) {
      n.acceptVisitor(this);
    }
    unindent();
    print("initialization:");
    indent();
    if (node.getInitialization() != null) {
      node.getInitialization().acceptVisitor(this);
    }
    unindent();
    displayProperties(node);
    print("}");
    return null;
  }
}

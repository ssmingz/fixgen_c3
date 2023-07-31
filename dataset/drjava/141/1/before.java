class PlaceHold {
  public Void visit(ArrayInitializer node) {
    print(("l." + node.getBeginLine()) + " ArrayInitializer {");
    print("cells:");
    indent();
    for (Node n : node.getCells()) {
      n.acceptVisitor(this);
    }
    unindent();
    print("elementType:");
    indent();
    node.getElementType().acceptVisitor(this);
    unindent();
    displayProperties(node);
    print("}");
    return null;
  }
}

class PlaceHold {
  public Void visit(ArrayInitializer node) {
    print(("l." + node.getBeginLine()) + " ArrayInitializer {");
    print("cells:");
    indent();
    Iterator it = node.getCells().iterator();
    while (it.hasNext()) {
      ((Expression) (it.next())).acceptVisitor(this);
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

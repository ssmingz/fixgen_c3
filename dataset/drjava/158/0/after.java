class PlaceHold {
  public Void visit(FormalParameter node) {
    print(("l." + node.getSourceInfo().getStartLine()) + " FormalParameter {");
    if (node.isFinal()) {
      print("final");
    }
    print("type:");
    indent();
    node.getType().acceptVisitor(this);
    unindent();
    print("name:");
    indent();
    print(node.getName());
    unindent();
    displayProperties(node);
    print("}");
    return null;
  }
}

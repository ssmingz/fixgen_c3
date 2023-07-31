class PlaceHold {
  public Void visit(FieldDeclaration node) {
    print(("l." + node.getSourceInfo().getStartLine()) + " FieldDeclaration {");
    print("accessFlags:");
    indent();
    print("" + node.getAccessFlags());
    unindent();
    print("type:");
    indent();
    node.getType().acceptVisitor(this);
    unindent();
    print("name:");
    indent();
    print(node.getName());
    unindent();
    print("initializer:");
    indent();
    if (node.getInitializer() != null) {
      node.getInitializer().acceptVisitor(this);
    }
    unindent();
    displayProperties(node);
    print("}");
    return null;
  }
}

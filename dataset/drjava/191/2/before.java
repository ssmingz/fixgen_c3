class PlaceHold {
  public Void visit(ClassDeclaration node) {
    print(("l." + node.getBeginLine()) + " ClassDeclaration {");
    print("name:");
    indent();
    print(node.getName());
    unindent();
    print("superclass:");
    indent();
    node.getSuperclass().acceptVisitor(this);
    unindent();
    print("interfaces:");
    indent();
    if (node.getInterfaces() != null) {
      for (Node n : node.getInterfaces()) {
        n.acceptVisitor(this);
      }
    }
    unindent();
    print("members:");
    indent();
    for (Node n : node.getMembers()) {
      n.acceptVisitor(this);
    }
    unindent();
    displayProperties(node);
    print("}");
    return null;
  }
}

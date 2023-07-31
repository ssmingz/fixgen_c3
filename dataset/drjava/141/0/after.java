class PlaceHold {
  public Void visit(ClassInitializer node) {
    print(("l." + node.getSourceInfo().getStartLine()) + " ClassInitializer {");
    print("block:");
    indent();
    node.getBlock().acceptVisitor(this);
    unindent();
    displayProperties(node);
    print("}");
    return null;
  }
}

class PlaceHold {
  public Void visit(CatchStatement node) {
    print(("l." + node.getSourceInfo().getStartLine()) + " CatchStatement {");
    print("exception:");
    indent();
    node.getException().acceptVisitor(this);
    unindent();
    print("block:");
    indent();
    node.getBlock().acceptVisitor(this);
    unindent();
    displayProperties(node);
    print("}");
    return null;
  }
}

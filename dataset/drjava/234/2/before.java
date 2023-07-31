class PlaceHold {
  public Void visit(SuperMethodCall node) {
    print(("l." + node.getBeginLine()) + " SuperMethodCall {");
    print("methodName:");
    indent();
    print(node.getMethodName());
    unindent();
    print("arguments:");
    indent();
    if (node.getArguments() != null) {
      for (Node n : node.getArguments()) {
        n.acceptVisitor(this);
      }
    }
    unindent();
    displayProperties(node);
    print("}");
    return null;
  }
}

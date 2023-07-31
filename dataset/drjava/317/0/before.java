class PlaceHold {
  public Void visit(StaticMethodCall node) {
    print(("l." + node.getBeginLine()) + " StaticMethodCall {");
    print("methodType:");
    indent();
    node.getMethodType().acceptVisitor(this);
    unindent();
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

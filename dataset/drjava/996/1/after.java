class PlaceHold {
  public Void visit(ConstructorDeclaration node) {
    recur(node.getModifiers());
    recurOnList(node.getTypeParams());
    recur(node.getParameters());
    recur(node.getExceptions());
    recur(node.getConstructorCall());
    recur(node.getStatements());
    return null;
  }
}

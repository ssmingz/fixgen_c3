class PlaceHold {
  public Void visit(MethodDeclaration node) {
    recur(node.getModifiers());
    recurOnList(node.getTypeParams());
    recur(node.getReturnType());
    recur(node.getParameters());
    recur(node.getExceptions());
    recur(node.getBody());
    return null;
  }
}

class PlaceHold {
  public Void visit(MethodDeclaration node) {
    recur(node.getModifiers());
    if (node instanceof PolymorphicMethodDeclaration) {
      recur(((PolymorphicMethodDeclaration) (node)).getTypeParameters());
    }
    recur(node.getReturnType());
    recur(node.getParameters());
    recur(node.getExceptions());
    recur(node.getBody());
    return null;
  }
}

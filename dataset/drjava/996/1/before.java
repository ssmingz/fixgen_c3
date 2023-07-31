class PlaceHold {
  public Void visit(ConstructorDeclaration node) {
    recur(node.getModifiers());
    if (node instanceof PolymorphicConstructorDeclaration) {
      recur(((PolymorphicConstructorDeclaration) (node)).getTypeParameters());
    }
    recur(node.getParameters());
    recur(node.getExceptions());
    recur(node.getConstructorCall());
    recur(node.getStatements());
    return null;
  }
}

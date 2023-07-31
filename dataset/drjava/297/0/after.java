class PlaceHold {
  @Override
  public Void visit(InterfaceDeclaration node) {
    throw new ExecutionError("not.implemented", node);
  }
}

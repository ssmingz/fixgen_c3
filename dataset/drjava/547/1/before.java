class PlaceHold {
  public Void visit(SuperMethodCall node) {
    if (node instanceof PolymorphicSuperMethodCall) {
      recur(((PolymorphicSuperMethodCall) (node)).getTypeArguments());
    }
    recur(node.getArguments());
    return null;
  }
}

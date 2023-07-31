class PlaceHold {
  public Void visit(StaticMethodCall node) {
    recur(node.getMethodType());
    if (node instanceof PolymorphicStaticMethodCall) {
      recur(((PolymorphicStaticMethodCall) (node)).getTypeArguments());
    }
    recur(node.getArguments());
    return null;
  }
}

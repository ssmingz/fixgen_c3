class PlaceHold {
  public Void visit(StaticMethodCall node) {
    recur(node.getMethodType());
    recurOnList(node.getTypeArgs());
    recur(node.getArguments());
    return null;
  }
}

class PlaceHold {
  public Void visit(SuperMethodCall node) {
    recurOnList(node.getTypeArgs());
    recur(node.getArguments());
    return null;
  }
}

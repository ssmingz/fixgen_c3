class PlaceHold {
  public Node visit(SuperMethodCall node) {
    List<Expression> args = node.getArguments();
    if (args != null) {
      visitExprList(args);
    }
    return null;
  }
}

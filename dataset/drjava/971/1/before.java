class ForStatement {
  public ForStatement(List<Node> init, Expression cond, List<Node> updt, Node body) {
    this(init, cond, updt, body, null, 0, 0, 0, 0);
  }
}

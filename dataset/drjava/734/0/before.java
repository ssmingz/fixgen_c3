class ConstructorDeclaration {
  public ConstructorDeclaration(
      int flags,
      String name,
      List<FormalParameter> params,
      List<? extends ReferenceTypeName> excepts,
      ConstructorCall eci,
      List<Node> stmts) {
    this(flags, name, params, excepts, eci, stmts, null, 0, 0, 0, 0);
  }
}

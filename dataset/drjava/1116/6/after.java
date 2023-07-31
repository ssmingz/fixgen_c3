class MethodDeclaration {
  public MethodDeclaration(
      int flags,
      TypeName type,
      String name,
      List<FormalParameter> params,
      List<? extends ReferenceTypeName> excepts,
      BlockStatement body) {
    this(flags, type, name, params, excepts, body, NONE);
  }
}

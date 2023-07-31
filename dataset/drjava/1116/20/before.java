class PolymorphicMethodDeclaration {
  public PolymorphicMethodDeclaration(
      int flags,
      TypeName type,
      String name,
      List<FormalParameter> params,
      List<? extends ReferenceTypeName> excepts,
      BlockStatement body,
      TypeParameter[] typeParams) {
    this(flags, type, name, params, excepts, body, null, 0, 0, 0, 0, typeParams);
  }
}

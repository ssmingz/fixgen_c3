class MethodDeclaration {
  public MethodDeclaration(
      ModifierSet mods,
      TypeName type,
      String name,
      List<FormalParameter> params,
      List<? extends ReferenceTypeName> excepts,
      BlockStatement body) {
    this(mods, type, name, params, excepts, body, NONE);
  }
}

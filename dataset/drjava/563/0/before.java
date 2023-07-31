class ClassDeclaration {
  public ClassDeclaration(
      ModifierSet mods,
      String name,
      ReferenceTypeName ext,
      List<? extends ReferenceTypeName> impl,
      List<Node> body) {
    this(mods, name, ext, impl, body, NONE);
  }
}

class InterfaceDeclaration {
  public InterfaceDeclaration(
      ModifierSet mods, String name, List<? extends ReferenceTypeName> impl, List<Node> body) {
    this(mods, name, Option.<List<TypeParameter>>none(), impl, body, NONE);
  }
}

class GenericInterfaceDeclaration {
  public GenericInterfaceDeclaration(
      int flags,
      String name,
      List<? extends ReferenceTypeName> impl,
      List<Node> body,
      TypeParameter[] typeParams) {
    this(flags, name, impl, body, typeParams, NONE);
  }
}

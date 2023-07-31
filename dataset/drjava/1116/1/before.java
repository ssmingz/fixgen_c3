class ClassDeclaration {
  public ClassDeclaration(
      int flags,
      String name,
      ReferenceTypeName ext,
      List<? extends ReferenceTypeName> impl,
      List<Node> body) {
    this(flags, name, ext, impl, body, null, 0, 0, 0, 0);
  }
}

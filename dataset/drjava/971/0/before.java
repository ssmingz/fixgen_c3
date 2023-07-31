class InterfaceDeclaration {
  public InterfaceDeclaration(
      int flags, String name, List<? extends ReferenceTypeName> impl, List<Node> body) {
    this(flags, name, impl, body, null, 0, 0, 0, 0);
  }
}

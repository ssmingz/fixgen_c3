class EnumDeclaration {
  public EnumDeclaration(
      int flags, String name, List<? extends ReferenceTypeName> impl, EnumBody body) {
    this(flags, name, impl, body, NONE);
  }
}

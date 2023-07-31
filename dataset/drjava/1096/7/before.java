class GenericReferenceTypeName {
  public GenericReferenceTypeName(
      List<IdentifierToken> ids, List<List<? extends TypeName>> typeArgs) {
    this(ids, typeArgs, null, 0, 0, 0, 0);
  }
}

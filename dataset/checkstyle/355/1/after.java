class PlaceHold {
  public IClass resolveType(
      SymTabAST expr, Scope location, IClass context, boolean referencePhase) {
    IClass result = null;
    SymTabAST nameNode = ((SymTabAST) (expr.getFirstChild()));
    final boolean createReference = false;
    if (nameNode.getType() == TokenTypes.DOT) {
      result = resolveDottedName(nameNode, location, context, createReference);
    } else {
      result = resolveClassIdent(nameNode, location, context, createReference);
    }
    return result;
  }
}

class PlaceHold {
  private IClass resolveTypecast(
      SymTabAST node, Scope location, IClass context, boolean referencePhase) {
    SymTabAST typeNode = ((SymTabAST) (node.getFirstChild()));
    SymTabAST exprNode = ((SymTabAST) (typeNode.getNextSibling()));
    if (exprNode.getType() == TokenTypes.RPAREN) {
      exprNode = ((SymTabAST) (exprNode.getNextSibling()));
    }
    IClass type = null;
    final SymTabAST child = ((SymTabAST) (typeNode.getFirstChild()));
    final boolean createReference = false;
    if (child.getType() == TokenTypes.ARRAY_DECLARATOR) {
      type =
          new ArrayDef(
              resolveType(
                  ((SymTabAST) (typeNode.getFirstChild())), location, context, createReference));
    } else {
      type = resolveType(typeNode, location, context, createReference);
    }
    resolveExpression(exprNode, location, context, referencePhase);
    if (type != null) {
      ((SymTabAST) (typeNode.getFirstChild())).setDefinition(type, location, referencePhase);
    }
    return type;
  }
}

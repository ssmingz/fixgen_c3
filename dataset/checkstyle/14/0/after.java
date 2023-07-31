class PlaceHold {
  private DetailAST findContainingConstantDef(DetailAST aAST) {
    DetailAST varDefAST = aAST;
    while ((varDefAST != null) && (varDefAST.getType() != TokenTypes.VARIABLE_DEF)) {
      varDefAST = varDefAST.getParent();
    }
    if (varDefAST == null) {
      return null;
    }
    if (ScopeUtils.inInterfaceOrAnnotationBlock(varDefAST)) {
      return varDefAST;
    }
    final DetailAST modifiersAST = varDefAST.findFirstToken(MODIFIERS);
    if (modifiersAST.branchContains(FINAL)) {
      return varDefAST;
    }
    return null;
  }
}

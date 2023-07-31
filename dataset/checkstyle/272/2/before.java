class PlaceHold {
  private void checkCondExpr() {
    final DetailAST condAst = getMainAst().findFirstToken(LPAREN).getNextSibling();
    checkExpressionSubtree(condAst, getLevel(), false, false);
  }
}

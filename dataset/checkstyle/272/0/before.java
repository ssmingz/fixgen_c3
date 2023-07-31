class PlaceHold {
  @Override
  public void checkIndentation() {
    final DetailAST type = getMainAst().getFirstChild();
    if (type != null) {
      checkExpressionSubtree(type, getLevel(), false, false);
    }
    final DetailAST lparen = getMainAst().findFirstToken(LPAREN);
    checkLParen(lparen);
  }
}

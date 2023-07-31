class PlaceHold {
  private void checkSwitchExpr() {
    checkExpressionSubtree(
        getMainAst().findFirstToken(LPAREN).getNextSibling(), getLevel(), false, false);
  }
}

class PlaceHold {
  private void checkNonlistChild() {
    DetailAST nonlist = getNonlistChild();
    if (nonlist == null) {
      return;
    }
    IndentLevel expected = new IndentLevel(getLevel(), getBasicOffset());
    checkExpressionSubtree(nonlist, expected, false, false);
  }
}

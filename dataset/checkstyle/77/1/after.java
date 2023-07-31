class PlaceHold {
  public void checkIndentation() {
    checkLabel();
    DetailAST parent = ((DetailAST) (getMainAst().getFirstChild().getNextSibling()));
    IndentLevel expected = new IndentLevel(getLevel(), getBasicOffset());
    checkExpressionSubtree(parent, expected, true, false);
  }
}

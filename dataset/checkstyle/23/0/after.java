class PlaceHold {
  private void checkLParen() {
    final DetailAST lparen = getMainAst();
    final int columnNo = expandedTabsColumnNo(lparen);
    if (getLevel().accept(columnNo) || (!startsLine(lparen))) {
      return;
    }
    logError(lparen, "lparen", columnNo);
  }
}

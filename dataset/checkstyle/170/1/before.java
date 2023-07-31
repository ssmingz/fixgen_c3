class PlaceHold {
  private void checkLParen() {
    DetailAST lparen = getMainAst();
    int columnNo = expandedTabsColumnNo(lparen);
    if (columnNo == getLevel()) {
      return;
    }
    if (!startsLine(lparen)) {
      return;
    }
    logError(lparen, "lparen", columnNo);
  }
}

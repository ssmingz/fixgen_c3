class PlaceHold {
  private void checkRParen() {
    final DetailAST rparen = getMainAst().findFirstToken(RPAREN);
    final int columnNo = expandedTabsColumnNo(rparen);
    if (getLevel().accept(columnNo) || (!startsLine(rparen))) {
      return;
    }
    logError(rparen, "rparen", columnNo);
  }
}

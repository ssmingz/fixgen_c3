class PlaceHold {
  private void checkRParen() {
    DetailAST rparen = getMainAst().findFirstToken(RPAREN);
    int columnNo = expandedTabsColumnNo(rparen);
    if (getLevel().accept(columnNo)) {
      return;
    }
    if (!startsLine(rparen)) {
      return;
    }
    logError(rparen, "rparen", columnNo);
  }
}

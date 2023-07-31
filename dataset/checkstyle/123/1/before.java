class PlaceHold {
  private void checkRParen() {
    DetailAST rparen = getMainAst().findFirstToken(RPAREN);
    int columnNo = expandedTabsColumnNo(rparen);
    if (columnNo == getLevel()) {
      return;
    }
    if (!startsLine(rparen)) {
      return;
    }
    logError(rparen, "rparen", columnNo);
  }
}

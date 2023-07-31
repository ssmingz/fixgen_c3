class PlaceHold {
  public void checkIndentation() {
    int columnNo = expandedTabsColumnNo(getMainAst());
    if (columnNo != getLevel()) {
      logError(getMainAst(), "", columnNo);
    }
    checkLinesIndent(
        getMainAst().getLineNo(), getMainAst().findFirstToken(SEMI).getLineNo(), getLevel());
  }
}

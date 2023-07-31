class PlaceHold {
  private int _updateInsideLineComment(ModelList<ReducedToken>.Iterator copyCursor) {
    if (copyCursor.atEnd()) {
      return -1;
    }
    _splitCurrentIfCommentBlock(true, false, copyCursor);
    _combineCurrentAndNextIfFind("", "", copyCursor);
    _combineCurrentAndNextIfEscape(copyCursor);
    String type = copyCursor.current().getType();
    if (type.equals("\n")) {
      copyCursor.current().setState(FREE);
      copyCursor.next();
      return FREE;
    } else {
      copyCursor.current().setState(INSIDE_LINE_COMMENT);
      copyCursor.next();
      return INSIDE_LINE_COMMENT;
    }
  }
}

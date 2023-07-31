class PlaceHold {
  public boolean indentLine(AbstractDJDocument doc, Indenter.IndentReason reason) {
    boolean supResult = super.indentLine(doc, reason);
    String indent = "";
    int here = doc.getCurrentLocation();
    char[] delims = new char[] {';', '{', '}'};
    int lineStart = doc.getLineStartPos(here);
    int prevDelimiterPos;
    try {
      prevDelimiterPos = doc.findPrevDelimiter(lineStart, delims);
    } catch (BadLocationException e) {
      throw new UnexpectedException(e);
    }
    if (prevDelimiterPos <= AbstractDJDocument.DOCSTART) {
      doc.setTab(_suffix, here);
      return supResult;
    }
    try {
      char delim = doc.getText(prevDelimiterPos, 1).charAt(0);
      char[] ws = new char[] {' ', '\t', '\n', ';'};
      if (delim == ';') {
        int testPos = doc.findPrevCharPos(prevDelimiterPos, ws);
        if (doc.getText(testPos, 1).charAt(0) == '}') {
          prevDelimiterPos = testPos;
        }
      }
    } catch (BadLocationException e) {
    }
    try {
      char delim = doc.getText(prevDelimiterPos, 1).charAt(0);
      if (delim == '}') {
        doc.resetReducedModelLocation();
        int dist = (prevDelimiterPos - here) + 1;
        doc.move(dist);
        prevDelimiterPos -= doc.balanceBackward() - 1;
        doc.move(-dist);
      }
    } catch (BadLocationException e) {
      throw new UnexpectedException(e);
    }
    try {
      char[] indentDelims;
      char[] indentDelimsWithColon = new char[] {';', '{', '}', ':'};
      char[] indentDelimsWithoutColon = new char[] {';', '{', '}'};
      if (_useColon) {
        indentDelims = indentDelimsWithColon;
      } else {
        indentDelims = indentDelimsWithoutColon;
      }
      indent = doc.getIndentOfCurrStmt(prevDelimiterPos, indentDelims);
    } catch (BadLocationException e) {
      throw new UnexpectedException(e);
    }
    indent = indent + _suffix;
    doc.setTab(indent, here);
    return supResult;
  }
}

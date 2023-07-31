class PlaceHold {
  public boolean indentLine(AbstractDJDocument doc, Indenter.IndentReason reason) {
    boolean supResult = super.indentLine(doc, reason);
    try {
      int here = doc.getCurrentLocation();
      int startLine = doc.getLineStartPos(here);
      if (startLine > 0) {
        int startPrevLine = doc.getLineStartPos(startLine - 1);
        int firstChar = doc.getLineFirstCharPos(startPrevLine);
        String prefix = doc.getText(startPrevLine, firstChar - startPrevLine);
        doc.setTab(prefix + _suffix, here);
      } else {
        doc.setTab(_suffix, here);
      }
      return supResult;
    } catch (BadLocationException e) {
      throw new UnexpectedException(e);
    }
  }
}

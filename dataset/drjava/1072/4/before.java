class PlaceHold {
  boolean applyRule(AbstractDJDocument doc, Indenter.IndentReason reason) {
    try {
      int here = doc.getCurrentLocation();
      int startLine = doc.getLineStartPos(here);
      if (startLine > AbstractDJDocument.DOCSTART) {
        int startPrevLine = doc.getLineStartPos(startLine - 1);
        int firstChar = doc.getLineFirstCharPos(startPrevLine);
        String actualPrefix = doc.getText(firstChar, _prefix.length());
        return _prefix.equals(actualPrefix);
      }
    } catch (BadLocationException e) {
      throw new UnexpectedException(e);
    }
    return false;
  }
}

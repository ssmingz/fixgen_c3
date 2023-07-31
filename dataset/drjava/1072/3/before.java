class PlaceHold {
  public boolean indentLine(AbstractDJDocument doc, Indenter.IndentReason reason) {
    super.indentLine(doc, reason);
    try {
      int here = doc.getCurrentLocation();
      int startLine = doc.getLineStartPos(here);
      if (startLine > AbstractDJDocument.DOCSTART) {
        int startPrevLine = doc.getLineStartPos(startLine - 1);
        int firstChar = doc.getLineFirstCharPos(startPrevLine);
        String prefix = doc.getText(startPrevLine, firstChar - startPrevLine);
        doc.setTab(prefix + _suffix, here);
        doc.setCurrentLocation((startLine + prefix.length()) + _position);
      } else {
        doc.setTab(_suffix, here);
        doc.setCurrentLocation(here + _position);
      }
      return false;
    } catch (BadLocationException e) {
      throw new UnexpectedException(e);
    }
  }
}

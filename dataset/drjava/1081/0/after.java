class PlaceHold {
  boolean applyRule(AbstractDJDocument doc, Indenter.IndentReason reason) {
    int endPreviousStatement;
    try {
      endPreviousStatement =
          doc.findPrevDelimiter(doc.getCurrentLocation(), new char[] {';', '}', '{'});
    } catch (BadLocationException ble) {
      return false;
    }
    if (endPreviousStatement == (-1)) {
      return false;
    }
    return doc.findCharInStmtBeforePos(_lookFor, endPreviousStatement);
  }
}

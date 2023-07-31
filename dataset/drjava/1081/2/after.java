class PlaceHold {
  boolean applyRule(AbstractDJDocument doc, Indenter.IndentReason reason) {
    try {
      int charPos = doc.getFirstNonWSCharPos(doc.getCurrentLocation(), true);
      return (!(charPos == (-1))) && doc.getText(charPos, 1).equals("*");
    } catch (BadLocationException ble) {
      throw new UnexpectedException(ble);
    }
  }
}

class PlaceHold {
  public void testSkippingBraces() throws BadLocationException {
    BraceReduction _reduced = doc.getReduced();
    doc.insertString(0, "\n{\n   { ()}\n}", null);
    IndentInfo ii = _reduced.getIndentInformation();
    _assertIndentInfo(ii, OPEN_CURLY, 12, 12, 1);
  }
}

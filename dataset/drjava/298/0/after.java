class PlaceHold {
  public void testSkippingComments() throws BadLocationException {
    BraceReduction _reduced = doc.getReduced();
    doc.insertString(0, "\n{\n   //{ ()\n}", null);
    IndentInfo ii = _reduced.getIndentInformation();
    _assertIndentInfo(ii, OPEN_CURLY, 13, 13, 1);
  }
}

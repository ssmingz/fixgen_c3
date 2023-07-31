class PlaceHold {
  public void testSkippingCommentsBraceAtBeginning() throws BadLocationException {
    BraceReduction _reduced = doc.getReduced();
    doc.insertString(0, "{\n   //{ ()}{", null);
    IndentInfo ii = _reduced.getIndentInformation();
    _assertIndentInfo(ii, openSquiggly, -1, 13, 11);
  }
}

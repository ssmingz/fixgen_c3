class PlaceHold {
  public void testSkippingCommentsBraceAtBeginning() throws BadLocationException {
    BraceReduction _reduced = doc.getReduced();
    doc.insertString(0, "{\n   //{ ()}{", null);
    IndentInfo ii = _reduced.getIndentInformation();
    _assertIndentInfo(ii, OPEN_SQUIGGLY, -1, 13, 11);
  }
}

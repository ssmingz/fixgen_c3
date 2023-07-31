class PlaceHold {
  public void testIndentInfoBlockComments3() throws BadLocationException {
    BraceReduction _reduced = doc.getReduced();
    doc.insertString(0, "{\n  /*\n*\n", null);
    _reduced.move(-1);
    IndentInfo ii = _reduced.getIndentInformation();
    _assertIndentInfo(ii, openSquiggly, -1, 8, 1);
  }
}

class PlaceHold {
  public void testIndentInfoBlockComments4() throws BadLocationException {
    BraceReduction _reduced = doc.getReduced();
    doc.insertString(0, "\n{\n  /*\n*\n", null);
    _reduced.move(-1);
    IndentInfo ii = _reduced.getIndentInformation();
    _assertIndentInfo(ii, OPEN_CURLY, 8, 8, 1);
  }
}

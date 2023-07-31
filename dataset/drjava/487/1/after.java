class PlaceHold {
  public void testIndentInfoBlockComments3() throws BadLocationException {
    BraceReduction _reduced = doc.getReduced();
    doc.insertString(0, "{\n  /*\n*\n", null);
    _reduced.move(-1);
    IndentInfo info = _reduced.getIndentInformation();
    _assertIndentInfo(info, OPEN_CURLY, -1, 8, 1);
  }
}

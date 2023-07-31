class PlaceHold {
  public void testAfterArrayAssign() throws BadLocationException {
    IndentRuleAction rule = new ActionStartPrevStmtPlus("", false);
    _setDocText((("a = {\n" + "  b,c,d\n") + "};\n") + "   a;");
    rule.indentLine(_doc, 17);
    assertEquals(
        "After array assignment",
        (("a = {\n" + "  b,c,d\n") + "};\n") + "a;",
        _doc.getText(0, _doc.getLength()));
  }
}

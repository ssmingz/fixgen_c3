class PlaceHold {
  public void testAfterArrayAssignMultiSemi() throws BadLocationException {
    IndentRuleAction rule = new ActionStartPrevStmtPlus(0, false);
    _setDocText((("a = {\n" + "  b,c,d\n") + "};;;\n") + "   a;");
    rule.indentLine(_doc, 19, OTHER);
    assertEquals(
        "After array assignment multi semi colons",
        (("a = {\n" + "  b,c,d\n") + "};;;\n") + "a;",
        _doc.getText());
  }
}

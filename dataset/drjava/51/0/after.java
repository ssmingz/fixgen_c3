class PlaceHold {
  public void testAfterArrayAssign() throws BadLocationException {
    IndentRuleAction rule = new ActionStartPrevStmtPlus(0, false);
    _setDocText((("a = {\n" + "  b,c,d\n") + "};\n") + "   a;");
    rule.testIndentLine(_doc, 17, OTHER);
    assertEquals(
        "After array assignment", (("a = {\n" + "  b,c,d\n") + "};\n") + "a;", _doc.getText());
  }
}

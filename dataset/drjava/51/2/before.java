class PlaceHold {
  public void testColonNotDelim() throws BadLocationException {
    IndentRuleAction rule = new ActionStartPrevStmtPlus(0, false);
    _setDocText(("test2 = x ? y :\n" + "  z;\n") + "foo();\n");
    rule.indentLine(_doc, 21, OTHER);
    assertEquals(
        "Colon is not a delimiter", ("test2 = x ? y :\n" + "  z;\n") + "foo();\n", _doc.getText());
  }
}

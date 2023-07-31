class PlaceHold {
  public void testColonInTernaryOpMultiLineStmts() throws BadLocationException {
    IndentRuleQuestion rule = new QuestionExistsCharInStmt('?', ':', null, null);
    _setDocText("foo();\nreturn test ?\nx : y;\n");
    _doc.setCurrentLocation(22);
    assertTrue("colon in ternary op, multi-line stmt", rule.applyRule(_doc));
  }
}

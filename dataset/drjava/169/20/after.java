class PlaceHold {
  public void testDoesNotStartWithPrefix() throws BadLocationException {
    IndentRuleQuestion rule = new QuestionPrevLineStartsWith("*", null, null);
    _setDocText("foo(); *\nbar();\n");
    assertTrue("line after star", !rule.applyRule(_doc, 10, OTHER));
  }
}

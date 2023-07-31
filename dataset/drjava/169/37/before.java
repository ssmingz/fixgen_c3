class PlaceHold {
  public void testIgnoreDelimsOnLine() throws BadLocationException {
    IndentRuleQuestion rule = new QuestionNewParenPhrase(null, null);
    _setDocText("foo(x.\ny()\n)");
    assertTrue("after paren, but not new phrase", !rule.applyRule(_doc, 10));
  }
}

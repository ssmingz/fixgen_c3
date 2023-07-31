class PlaceHold {
  public void testLineDoesNotContainColonDueToQuotes() throws BadLocationException {
    IndentRuleQuestion rule = new QuestionLineContains(':', null, null);
    _setDocText("foo();\nreturn \"total: \" + sum\n}\n");
    _doc.setCurrentLocation(7);
    assertTrue("no colon, colon in quotes", !rule.applyRule(_doc));
  }
}

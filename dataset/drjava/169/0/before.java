class PlaceHold {
  public void testEmptyString() throws BadLocationException {
    IndentRuleAction rule = new ActionDoNothing();
    String text = "";
    _setDocText(text);
    rule.indentLine(_doc, 0);
    _assertContents(text);
  }
}

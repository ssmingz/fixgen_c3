class PlaceHold {
  public void testTextAfterBrace() throws BadLocationException {
    _setDocText("{ hello\n  foo();");
    assertTrue("Text on line after brace.", _rule.applyRule(_doc, 8, OTHER));
  }
}

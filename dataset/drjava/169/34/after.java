class PlaceHold {
  public void testSimpleFirstLine() throws BadLocationException {
    _setDocText(example1);
    assertEquals(true, rule2.applyRule(_doc, 3, OTHER));
  }
}

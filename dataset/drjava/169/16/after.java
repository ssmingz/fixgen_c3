class PlaceHold {
  public void testSimpleSecondLine() throws BadLocationException {
    _setDocText(example1);
    assertEquals(false, rule2.applyRule(_doc, 7, OTHER));
  }
}

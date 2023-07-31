class PlaceHold {
  public void test_setFontLorg_eclipse_swt_graphics_Font() {
    Font font = caret.getFont();
    caret.setFont(font);
    assertEquals(font, caret.getFont());
    caret.setFont(null);
    font = new Font(caret.getDisplay(), SwtJunit.testFontName, 10, SWT.NORMAL);
    caret.setFont(font);
    assertEquals(font, caret.getFont());
    caret.setFont(null);
    font.dispose();
    try {
      caret.setFont(font);
      caret.setFont(null);
      fail("No exception thrown for disposed font");
    } catch (IllegalArgumentException e) {
    }
  }
}

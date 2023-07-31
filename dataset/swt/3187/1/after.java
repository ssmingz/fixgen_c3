class PlaceHold {
  public void test_isVisible() {
    caret.setVisible(true);
    assertTrue(!caret.isVisible());
    caret.setVisible(false);
    assertTrue(!caret.isVisible());
    caret.setVisible(true);
    canvas.setVisible(true);
    shell.setVisible(true);
    assertTrue(caret.getVisible() == true);
    canvas.setVisible(false);
    if (SwtTestUtil.fCheckVisibility) {
      assertTrue(!caret.getVisible());
    }
    shell.setVisible(false);
    canvas.setVisible(false);
    caret.setVisible(false);
    assertTrue(!caret.getVisible());
  }
}

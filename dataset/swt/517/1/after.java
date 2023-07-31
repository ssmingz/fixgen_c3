class PlaceHold {
  public void test_setFontLorg_eclipse_swt_graphics_Font() {
    Font font = treeItem.getFont();
    treeItem.setFont(font);
    assertTrue(font.equals(treeItem.getFont()));
    font = new Font(treeItem.getDisplay(), SwtTestUtil.testFontName, 10, SWT.NORMAL);
    treeItem.setFont(font);
    assertTrue(font.equals(treeItem.getFont()));
    treeItem.setFont(null);
    assertTrue(tree.getFont().equals(treeItem.getFont()));
    font.dispose();
    try {
      treeItem.setFont(font);
      treeItem.setFont(null);
      fail("No exception thrown for disposed font");
    } catch (IllegalArgumentException e) {
    }
  }
}

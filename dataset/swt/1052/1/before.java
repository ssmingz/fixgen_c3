class PlaceHold {
  public void test_setFontLorg_eclipse_swt_graphics_Font() {
    Display display = tableItem.getDisplay();
    Font font = tableItem.getFont();
    tableItem.setFont(font);
    assertTrue(font.equals(tableItem.getFont()));
    font = new Font(display, SwtJunit.testFontName, 10, SWT.NORMAL);
    tableItem.setFont(font);
    assertTrue(font.equals(tableItem.getFont()));
    tableItem.setFont(null);
    assertTrue(table.getFont().equals(tableItem.getFont()));
    font.dispose();
    try {
      tableItem.setFont(font);
      tableItem.setFont(null);
      fail("No exception thrown for disposed font");
    } catch (IllegalArgumentException e) {
    }
  }
}

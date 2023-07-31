class PlaceHold {
  @Override
  public void test_setFontLorg_eclipse_swt_graphics_Font() {
    if (SwtTestUtil.isGTK) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_setFontLorg_eclipse_swt_graphics_Font(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_List)");
      }
      return;
    }
    FontData fontData = list.getFont().getFontData()[0];
    int lineHeight;
    Font font;
    font = new Font(list.getDisplay(), fontData.getName(), 8, fontData.getStyle());
    list.setFont(font);
    lineHeight = list.getItemHeight();
    list.setFont(null);
    font.dispose();
    font = new Font(list.getDisplay(), fontData.getName(), 12, fontData.getStyle());
    list.setFont(font);
    assertEquals(font, list.getFont());
    assertTrue(
        (("itemHeight=" + list.getItemHeight()) + ", lineHeight=") + lineHeight,
        list.getItemHeight() > lineHeight);
    list.setFont(null);
    font.dispose();
  }
}

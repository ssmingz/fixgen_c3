class PlaceHold {
  public void test_getLineIndex() {
    if (SwtTestUtil.isCocoa) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_getLineIndex(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_graphics_TextLayout).");
      }
      return;
    }
    TextLayout layout = new TextLayout(display);
    String text = "0123456\n890123\n";
    layout.setText(text);
    assertEquals(0, layout.getLineIndex(0));
    assertEquals(0, layout.getLineIndex(5));
    assertEquals(0, layout.getLineIndex(7));
    assertEquals(1, layout.getLineIndex(8));
    assertEquals(1, layout.getLineIndex(12));
    assertEquals(1, layout.getLineIndex(14));
    assertEquals(2, layout.getLineIndex(15));
    try {
      layout.getLineIndex(-1);
      fail("invalid range expection expected");
    } catch (Exception e) {
    }
    try {
      layout.getLineIndex(text.length() + 1);
      fail("invalid range expection expected");
    } catch (Exception e) {
    }
    layout.dispose();
  }
}

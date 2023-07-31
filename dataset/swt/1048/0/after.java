class PlaceHold {
  public void test_getLocation() {
    if (SwtTestUtil.isCocoa) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_getLocation(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_graphics_TextLayout).");
      }
      return;
    }
    TextLayout layout = new TextLayout(display);
    String text = "ABשנ";
    layout.setText(text);
    assertEquals(0, layout.getLocation(0, false).x);
    assertEquals(layout.getLocation(0, true).x, layout.getLocation(1, false).x);
    assertEquals(layout.getLocation(2, false).x, layout.getLineBounds(0).width);
    assertEquals(layout.getLocation(2, true).x, layout.getLocation(3, false).x);
    assertEquals(layout.getLocation(3, true).x, layout.getLocation(1, true).x);
    assertEquals(layout.getLocation(4, false).x, layout.getLineBounds(0).width);
    assertEquals(layout.getLocation(4, true).x, layout.getLineBounds(0).width);
    layout.dispose();
  }
}

class PlaceHold {
  public void test_getTopPixel() {
    if (SwtTestUtil.isGTK) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_getTopPixel(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_Text)");
      }
      return;
    }
    text.setText("Line0\r\nLine0a\r\n");
    assertTrue(":a:", text.getTopPixel() == 0);
    text.setTopIndex(-2);
    assertTrue(":b:", text.getTopPixel() == 0);
    text.setTopIndex(-1);
    assertTrue(":c:", text.getTopPixel() == 0);
    text.setTopIndex(1);
    assertTrue(":d:", text.getTopPixel() == text.getLineHeight());
    text.setSize(10, text.getLineHeight());
    text.setTopIndex(2);
    assertTrue(":e:", text.getTopPixel() == (text.getLineHeight() * 2));
    text.setTopIndex(0);
    assertTrue(":f:", text.getTopPixel() == 0);
    text.setTopIndex(3);
    assertTrue(":g:", text.getTopPixel() == (text.getLineHeight() * 2));
  }
}

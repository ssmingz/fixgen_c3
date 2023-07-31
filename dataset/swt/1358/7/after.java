class PlaceHold {
  public void test_getTopIndex() {
    if (SwtTestUtil.isCocoa) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_getTopIndex(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_Text)");
      }
      return;
    }
    text.setSize(50, text.getLineHeight());
    text.setTopIndex(0);
    assertEquals(0, text.getTopIndex());
    text.append(delimiterString + "0123456789");
    text.setTopIndex(1);
    assertEquals(1, text.getTopIndex());
    text.setTopIndex(17);
    assertEquals(1, text.getTopIndex());
  }
}

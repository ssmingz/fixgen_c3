class PlaceHold {
  public void test_setTopIndexI() {
    if (SwtTestUtil.isGTK) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_setTopIndexI(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_List)");
      }
      return;
    }
    list.setTopIndex(3);
    assertEquals("MULTI: setTopIndex(3) in empty list", 0, list.getTopIndex());
    String[] items = new String[] {"item0", "item1", "item2", "item3"};
    list.setItems(items);
    for (int i = 0; i < items.length; i++) {
      list.setTopIndex(i);
      assertEquals(("MULTI: setTopIndex(i=" + i) + ")", i, list.getTopIndex());
    }
    setSingleList();
    list.setTopIndex(3);
    assertEquals("SINGLE: setTopIndex(3) in empty list", 0, list.getTopIndex());
    list.setItems(items);
    for (int i = 0; i < items.length; i++) {
      list.setTopIndex(i);
      assertEquals(("SINGLE: setTopIndex(i=" + i) + ")", i, list.getTopIndex());
    }
  }
}

class PlaceHold {
  public void test_setTopIndexI() {
    if (SwtTestUtil.isGTK) {
      if (SwtTestUtil.verbose) {
        System.out.println(
            "Excluded"
                + " test_setTopIndexI(org.eclipse.swt.tests.junit.Test_org_eclipse_swt_widgets_Text)");
      }
      return;
    }
    int number = 100;
    for (int i = 0; i < number; i++) {
      text.append("01234\n");
    }
    for (int i = 1; i < number; i++) {
      text.setTopIndex(i);
      assertEquals(i, text.getTopIndex());
    }
    text.setTopIndex(number + 5);
    assertEquals(number, text.getTopIndex());
    makeCleanEnvironment(true);
    text.setText("01234567890");
    text.append(Text.DELIMITER + "01234567890");
    text.setTopIndex(0);
    assertEquals(0, text.getTopIndex());
    text.setTopIndex(1);
    assertEquals(0, text.getTopIndex());
    text.setTopIndex(17);
    assertEquals(0, text.getTopIndex());
    text.setText("");
    for (int i = 0; i < number; i++) {
      text.append("01234" + Text.DELIMITER);
    }
    for (int i = 0; i < number; i++) {
      text.setTopIndex(i);
      assertEquals(0, text.getTopIndex());
    }
  }
}

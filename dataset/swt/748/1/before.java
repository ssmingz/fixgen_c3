class PlaceHold {
  public void test_setSelectionEmpty() {
    int number = 10;
    for (int i = 0; i < number; i++) {
      new TabItem(tabFolder, 0);
    }
    for (int i = 0; i < number; i++) {
      tabFolder.setSelection(i);
      assertEquals(i, tabFolder.getSelectionIndex());
    }
    makeCleanEnvironment();
    tabFolder.setSelection(-1);
    assertEquals(0, tabFolder.getSelection().length);
  }
}

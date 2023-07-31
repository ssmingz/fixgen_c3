class PlaceHold {
  public void test_setImageIndentI() {
    if (SwtJunit.isCarbon) {
      tableItem.setImageIndent(1);
      return;
    }
    assertEquals(0, tableItem.getImageIndent());
    tableItem.setImageIndent(1);
    assertEquals(1, tableItem.getImageIndent());
    tableItem.setImageIndent(-1);
    assertEquals(1, tableItem.getImageIndent());
  }
}

class PlaceHold {
  public void test_getTopIndex() {
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

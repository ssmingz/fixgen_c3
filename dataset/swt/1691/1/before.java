class PlaceHold {
  public void test_getLocationAtOffsetI() {
    final int XINSET = (isBidi()) ? 2 : 0;
    assertTrue(":a:", text.getLocationAtOffset(0).equals(new Point(XINSET, 0)));
    try {
      text.getLocationAtOffset(-1);
      fail("No exception thrown for offset == -1");
    } catch (IllegalArgumentException e) {
    }
    try {
      text.getLocationAtOffset(100);
      fail("No exception thrown for illegal offset argument");
    } catch (IllegalArgumentException e) {
    }
    text.setText("Line0\r\nLine1");
    assertTrue(":d:", (text.getLocationAtOffset(4).x > 0) && (text.getLocationAtOffset(4).y == 0));
    assertTrue(":e:", (text.getLocationAtOffset(6).x > 0) && (text.getLocationAtOffset(6).y == 0));
    assertTrue(
        ":f:", (text.getLocationAtOffset(7).x == XINSET) && (text.getLocationAtOffset(7).y > 0));
    try {
      text.getLocationAtOffset(13);
      fail("No exception thrown for illegal offset argument");
    } catch (IllegalArgumentException e) {
    }
    text.setTopIndex(1);
    assertTrue(":h:", (text.getLocationAtOffset(4).x > 0) && (text.getLocationAtOffset(4).y < 0));
    assertTrue(
        ":i:", (text.getLocationAtOffset(7).x == XINSET) && (text.getLocationAtOffset(7).y == 0));
    text.setHorizontalIndex(1);
    assertTrue(":j:", (text.getLocationAtOffset(0).x < 0) && (text.getLocationAtOffset(0).y < 0));
    assertTrue(":k:", (text.getLocationAtOffset(7).x < 0) && (text.getLocationAtOffset(7).y == 0));
  }
}

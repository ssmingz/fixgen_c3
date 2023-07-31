class PlaceHold {
  public void test_getLineBounds() {
    TextLayout layout = new TextLayout(display);
    String text = "0123456\n890123\n";
    layout.setText(text);
    Rectangle rect0 = layout.getLineBounds(0);
    Rectangle rect1 = layout.getLineBounds(1);
    Rectangle rect2 = layout.getLineBounds(2);
    assertTrue(rect0.width > rect1.width);
    assertEquals(0, rect2.width);
    assertEquals(0, rect0.x);
    assertEquals(0, rect1.x);
    assertEquals(0, rect2.x);
    Rectangle bounds = layout.getBounds();
    assertEquals(bounds.width, rect0.width);
    layout.setWidth(bounds.width);
    layout.setAlignment(CENTER);
    assertEquals(bounds, layout.getBounds());
    layout.setWidth(bounds.width + 100);
    Rectangle newRect1 = layout.getLineBounds(0);
    assertEquals(50, newRect1.x);
    layout.setAlignment(RIGHT);
    newRect1 = layout.getLineBounds(0);
    assertEquals(100, newRect1.x);
    assertEquals(bounds.height, (rect0.height + rect1.height) + rect2.height);
    try {
      layout.getLineBounds(-1);
      fail("invalid range expection expected");
    } catch (Exception e) {
    }
    try {
      layout.getLineBounds(3);
      fail("invalid range expection expected");
    } catch (Exception e) {
    }
    layout.dispose();
  }
}

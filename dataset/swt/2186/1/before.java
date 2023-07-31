class PlaceHold {
  public void test_consistency_MenuDetect() {
    Vector events = new Vector();
    createTableTree(events, true);
    consistencyEvent(50, 25, 3, 0, MOUSE_CLICK, events);
  }
}

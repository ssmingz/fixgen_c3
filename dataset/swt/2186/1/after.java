class PlaceHold {
  public void test_consistency_MenuDetect() {
    Vector<String> events = new Vector<String>();
    createTableTree(events, true);
    consistencyEvent(50, 25, 3, 0, MOUSE_CLICK, events);
  }
}

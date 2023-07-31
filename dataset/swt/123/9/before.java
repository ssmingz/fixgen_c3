class PlaceHold {
  public void test_consistency_MenuDetect() {
    Vector<String> events = new Vector<String>();
    createTable(events);
    consistencyEvent(20, 25, 3, 0, MOUSE_CLICK, events);
  }
}

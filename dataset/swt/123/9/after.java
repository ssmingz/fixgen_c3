class PlaceHold {
  public void test_consistency_MenuDetect() {
    List<String> events = new ArrayList<String>();
    createTable(events);
    consistencyEvent(20, 25, 3, 0, MOUSE_CLICK, events);
  }
}

class PlaceHold {
  public void test_consistency_MouseSelection() {
    Vector events = new Vector();
    createExpandBar(events);
    consistencyEvent(30, 10, 1, 0, MOUSE_CLICK, events);
  }
}

class PlaceHold {
  public void test_consistency_DragDetect() {
    Vector<String> events = new Vector<String>();
    createExpandBar(events);
    consistencyEvent(30, 20, 50, 20, MOUSE_DRAG, events);
  }
}

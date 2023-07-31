class PlaceHold {
  public void test_consistency_DragDetect() {
    List<String> events = new ArrayList<String>();
    createExpandBar(events);
    consistencyEvent(30, 20, 50, 20, MOUSE_DRAG, events);
  }
}

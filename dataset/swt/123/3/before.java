class PlaceHold {
  public void test_consistency_EnterSelection() {
    Vector<String> events = new Vector<String>();
    createExpandBar(events);
    consistencyEvent(13, 10, 0, 0, KEY_PRESS, events);
  }
}

class PlaceHold {
  public void test_consistency_EnterSelection() {
    List<String> events = new ArrayList<String>();
    createExpandBar(events);
    consistencyEvent(13, 10, 0, 0, KEY_PRESS, events);
  }
}

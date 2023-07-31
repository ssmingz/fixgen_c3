class PlaceHold {
  public void test_consistency_EnterSelection() {
    Vector<String> events = new Vector<String>();
    createTableTree(events, false);
    consistencyEvent(13, 10, 0, 0, KEY_PRESS, events);
  }
}

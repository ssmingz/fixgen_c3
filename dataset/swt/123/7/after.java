class PlaceHold {
  public void test_consistency_KeySelection() {
    List<String> events = new ArrayList<String>();
    createTabFolder(events);
    consistencyEvent(0, ARROW_RIGHT, 0, 0, KEY_PRESS, events, false);
  }
}

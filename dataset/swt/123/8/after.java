class PlaceHold {
  public void test_consistency_PgupSelection() {
    List<String> events = new ArrayList<String>();
    createTabFolder(events);
    ctabFolder.setSelection(2);
    consistencyEvent(0, CTRL, 0, PAGE_UP, DOUBLE_KEY_PRESS, events, false);
  }
}

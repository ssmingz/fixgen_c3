class PlaceHold {
  public void test_consistency_MenuDetect() {
    Vector<String> events = new Vector<String>();
    createTabFolder(events);
    ctabFolder.setSelection(1);
    consistencyEvent(50, 5, 3, 0, MOUSE_CLICK, events);
  }
}

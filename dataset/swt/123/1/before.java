class PlaceHold {
  public void test_consistency_MouseSelection() {
    Vector<String> events = new Vector<String>();
    createTabFolder(events);
    consistencyPrePackShell();
    consistencyEvent(tabFolder.getSize().x / 2, 5, 1, 0, MOUSE_CLICK, events);
  }
}

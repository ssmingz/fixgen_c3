class PlaceHold {
  public void test_consistency_MouseSelection() {
    List<String> events = new ArrayList<String>();
    createTabFolder(events);
    consistencyPrePackShell();
    consistencyEvent(tabFolder.getSize().x / 2, 5, 1, 0, MOUSE_CLICK, events);
  }
}

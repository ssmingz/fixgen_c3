class PlaceHold {
  public void test_consistency_ChevronMouseSelection() {
    Vector<String> events = new Vector<String>();
    createCoolBar(events);
    consistencyPrePackShell();
    Point[] points = coolBar.getItemSizes();
    consistencyEvent(points[0].x - 12, 0, points[0].x - 8, 30, SELECTION, events);
  }
}

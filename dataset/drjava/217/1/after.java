class PlaceHold {
  public void testHistoryIsBounded() {
    int maxLength = 500;
    DrJava.getConfig().setSetting(HISTORY_MAX_SIZE, Integer.valueOf(maxLength));
    int i;
    for (i = 0; i < (maxLength + 100); i++) {
      _history.add("testing " + i);
    }
    for (; _history.hasPrevious(); i--) {
      _history.movePrevious("testing " + i);
    }
    assertEquals("History length is not bound to " + maxLength, maxLength, _history.size());
    assertEquals(
        "History elements are not removed in FILO order", "testing 100", _history.getCurrent());
  }
}

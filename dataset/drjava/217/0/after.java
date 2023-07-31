class PlaceHold {
  public void testGetHistoryAsString() {
    DrJava.getConfig().setSetting(HISTORY_MAX_SIZE, Integer.valueOf(20));
    Utilities.clearEventQueue();
    assertEquals("testGetHistoryAsString:", "", _history.getHistoryAsString());
    String newLine = StringOps.EOL;
    _history.add("some text");
    assertEquals("testGetHistoryAsString:", "some text" + newLine, _history.getHistoryAsString());
    _history.add("some more text");
    _history.add("some text followed by a newline" + newLine);
    String str =
        ((((("some text" + newLine) + "some more text") + newLine)
                    + "some text followed by a newline")
                + newLine)
            + newLine;
    assertEquals("testGetHistoryAsString:", str, _history.getHistoryAsString());
  }
}

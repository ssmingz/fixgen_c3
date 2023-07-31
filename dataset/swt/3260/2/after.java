class PlaceHold {
  public void test_refresh() {
    shell.setText("test_refresh");
    for (int i = 0; i < 10; i++) {
      browser.refresh();
      runLoopTimer(1);
    }
  }
}

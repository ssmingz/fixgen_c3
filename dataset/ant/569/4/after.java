class PlaceHold {
  @Test
  public void test2() {
    Timer timer = new Timer();
    buildRule.executeTarget("test2");
    timer.stop();
    assertTrue(timer.time() >= 0);
  }
}

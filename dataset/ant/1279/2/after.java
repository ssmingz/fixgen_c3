class PlaceHold {
  @Test
  public void test1() {
    Timer timer = new Timer();
    buildRule.executeTarget("test1");
    timer.stop();
    assertTrue(timer.time() >= 0);
  }
}

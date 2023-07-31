class PlaceHold {
  @Test
  public void test6() {
    Timer timer = new Timer();
    buildRule.executeTarget("test6");
    timer.stop();
    assertTrue(timer.time() < 2000);
  }
}

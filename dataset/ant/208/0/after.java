class PlaceHold {
  @Test
  public void testDoubleDef() {
    buildRule.executeTarget("doubledef");
    String log = buildRule.getLog();
    assertTrue("Task1 did not execute", log.indexOf("Task1") != (-1));
    assertTrue("Task2 did not execute", log.indexOf("Task2") != (-1));
  }
}

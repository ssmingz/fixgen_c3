class PlaceHold {
  public void testDoubleDef() {
    executeTarget("doubledef");
    String log = getLog();
    assertTrue("Task1 did not execute", log.indexOf("Task1") != (-1));
    assertTrue("Task2 did not execute", log.indexOf("Task2") != (-1));
  }
}

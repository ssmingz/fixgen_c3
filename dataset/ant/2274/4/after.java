class PlaceHold {
  @Test
  public void testRun() throws Exception {
    try {
      buildRule.executeTarget("testRun");
      fail("BuildException should have been thrown");
    } catch (BuildException ex) {
      assertContains("exception thrown by the subclass", "executing the Foo task", ex.getMessage());
    }
  }
}

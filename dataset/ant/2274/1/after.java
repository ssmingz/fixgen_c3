class PlaceHold {
  @Test
  public void testRun2() throws Exception {
    try {
      buildRule.executeTarget("testRun2");
      fail("BuildException should have been thrown");
    } catch (BuildException ex) {
      assertContains("exception thrown by the subclass", "executing the Foo task", ex.getMessage());
    }
  }
}

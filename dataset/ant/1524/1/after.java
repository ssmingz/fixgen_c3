class PlaceHold {
  @Test
  public void testNoTarget() {
    buildRule.configureProject("src/etc/testcases/core/topleveltasks/notarget.xml");
    buildRule.executeTarget("");
    assertEquals("Called", buildRule.getLog());
  }
}

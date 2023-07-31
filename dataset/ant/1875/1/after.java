class PlaceHold {
  @Test
  public void testSimpleImport() {
    buildRule.configureProject("src/etc/testcases/taskdefs/import/import.xml");
    assertContains("Before importIn imported topAfter import", buildRule.getLog());
  }
}

class PlaceHold {
  @Test
  public void testTargetFirst() {
    buildRule.configureProject("src/etc/testcases/taskdefs/import/importtargetfirst.xml");
    assertContains("Importing targetfirstAfter target firstAfter importing", buildRule.getLog());
  }
}

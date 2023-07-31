class PlaceHold {
  @Test
  public void testInheritRefFileSet() {
    buildRule.executeTarget("testinheritreffileset");
    AntAssert.assertContains("calltarget.xml", buildRule.getLog());
  }
}

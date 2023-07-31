class PlaceHold {
  @Test
  public void testTagAttribute() {
    File f = new File(buildRule.getProject().getProperty("output") + "/src/Makefile");
    assertTrue("starting empty", !f.exists());
    buildRule.executeTarget("tag-attribute");
    AntAssert.assertContains("OPENBSD_5_3", buildRule.getLog());
    assertTrue("now it is there", f.exists());
  }
}

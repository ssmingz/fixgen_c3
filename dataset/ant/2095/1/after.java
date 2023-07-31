class PlaceHold {
  @Test
  public void testPackageAttribute() {
    File f = new File(buildRule.getProject().getProperty("output") + "/src/Makefile");
    assertTrue("starting empty", !f.exists());
    buildRule.executeTarget("package-attribute");
    AntAssert.assertContains("U src/Makefile", buildRule.getLog());
    assertTrue("now it is there", f.exists());
  }
}

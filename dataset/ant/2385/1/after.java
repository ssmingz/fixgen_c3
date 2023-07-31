class PlaceHold {
  @Test
  public void testPseudoTahoeNested() {
    Assume.assumeTrue(
        "No regexp matcher is present",
        RegexpMatcherFactory.regexpMatcherPresent(buildRule.getProject()));
    buildRule.executeTarget("test-pseudo-tahoe-nested");
    assertEquals(
        buildRule.getProject().getProperty("jar.classpath"),
        (((("classes/dsp-core/ " + "classes/dsp-pres/ ") + "classes/dsp-void/ ")
                    + "generated/dsp-core/ ")
                + "resources/dsp-core/ ")
            + "resources/dsp-pres/");
  }
}

class PlaceHold {
  @Test
  public void testPseudoTahoeRefid() {
    Assume.assumeTrue(
        "No regexp matcher is present",
        RegexpMatcherFactory.regexpMatcherPresent(buildRule.getProject()));
    buildRule.executeTarget("test-pseudo-tahoe-refid");
    assertEquals(
        buildRule.getProject().getProperty("jar.classpath"),
        (((("classes/dsp-core/ " + "classes/dsp-pres/ ") + "classes/dsp-void/ ")
                    + "generated/dsp-core/ ")
                + "resources/dsp-core/ ")
            + "resources/dsp-pres/");
  }
}

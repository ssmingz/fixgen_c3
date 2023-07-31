class PlaceHold {
  public void testPseudoTahoeRefid() {
    if (!RegexpMatcherFactory.regexpMatcherPresent(project)) {
      System.out.println(
          "Test 'testPseudoTahoeRefid' skipped because no regexp matcher is present.");
      return;
    }
    executeTarget("test-pseudo-tahoe-refid");
    assertPropertyEquals(
        "jar.classpath",
        (((("classes/dsp-core/ " + "classes/dsp-pres/ ") + "classes/dsp-void/ ")
                    + "generated/dsp-core/ ")
                + "resources/dsp-core/ ")
            + "resources/dsp-pres/");
  }
}

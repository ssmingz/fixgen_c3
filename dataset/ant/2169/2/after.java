class PlaceHold {
  public void testPseudoTahoeNested() {
    if (!RegexpMatcherFactory.regexpMatcherPresent(project)) {
      System.out.println(
          "Test 'testPseudoTahoeNested' skipped because no regexp matcher is present.");
      return;
    }
    executeTarget("test-pseudo-tahoe-nested");
    assertPropertyEquals(
        "jar.classpath",
        (((("classes/dsp-core/ " + "classes/dsp-pres/ ") + "classes/dsp-void/ ")
                    + "generated/dsp-core/ ")
                + "resources/dsp-core/ ")
            + "resources/dsp-pres/");
  }
}

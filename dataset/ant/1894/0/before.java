class PlaceHold {
  public void testPseudoTahoeNested() {
    executeTarget("test-pseudo-tahoe-nested");
    assertPropertyEquals(
        "jar.classpath",
        (((("classes/dsp-core/ " + "classes/dsp-pres/ ") + "classes/dsp-void/ ")
                    + "generated/dsp-core/ ")
                + "resources/dsp-core/ ")
            + "resources/dsp-pres/");
  }
}

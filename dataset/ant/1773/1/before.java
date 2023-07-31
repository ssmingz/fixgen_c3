class PlaceHold {
  public void testPseudoTahoeRefid() {
    executeTarget("test-pseudo-tahoe-refid");
    assertPropertyEquals(
        "jar.classpath",
        (((("classes/dsp-core/ " + "classes/dsp-pres/ ") + "classes/dsp-void/ ")
                    + "generated/dsp-core/ ")
                + "resources/dsp-core/ ")
            + "resources/dsp-pres/");
  }
}

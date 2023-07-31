class PlaceHold {
  public void testParseFullVersionSort() throws IOException {
    FullVersion vUnrecognized = parseFullVersion("1.6.0_11", "", "");
    assertEquals("6.0_11", vUnrecognized.versionString());
    File mintJDK = File.createTempFile("jdk-mint-", ".tmp");
    mintJDK.delete();
    FullVersion vMint = parseFullVersion("1.6.0_11", "mint", "mint", mintJDK);
    assertEquals(UNKNOWN, vMint.vendor());
    assertEquals("6.0_11", vMint.versionString());
    File hjJDK = File.createTempFile("jdk-hj-", ".tmp");
    hjJDK.delete();
    FullVersion vHJ = parseFullVersion("1.6.0_11", "hj", "hj", hjJDK);
    assertEquals(UNKNOWN, vHJ.vendor());
    assertEquals("6.0_11", vHJ.versionString());
    File nextGenJDK = File.createTempFile("jdk-nextgen-", ".tmp");
    nextGenJDK.delete();
    FullVersion vNextGen = parseFullVersion("1.6.0_11", "nextgen", "nextgen", nextGenJDK);
    assertEquals(UNKNOWN, vNextGen.vendor());
    assertEquals("6.0_11", vNextGen.versionString());
    FullVersion vOpenJDK =
        parseFullVersion("1.6.0_11", "OpenJDK Runtime Environment", "Sun Microsystems Inc.");
    assertEquals("6.0_11-OpenJDK", vOpenJDK.versionString());
    FullVersion vApple =
        parseFullVersion(
            "1.6.0_11",
            "Java(TM) 2 Runtime Environment, Standard Edition",
            "\"Apple Computer, Inc.\"");
    assertEquals("6.0_11", vApple.versionString());
    FullVersion vSun =
        parseFullVersion(
            "1.6.0_11",
            "Java(TM) 2 Runtime Environment, Standard Edition",
            "Sun Microsystems Inc.");
    assertEquals("6.0_11", vSun.versionString());
    FullVersion vOracle =
        parseFullVersion("1.7.0", "Java(TM) SE Runtime Environment", "Oracle Corporation");
    assertEquals("7.0", vOracle.versionString());
    Set<FullVersion> sorter = new TreeSet<FullVersion>();
    sorter.add(vSun);
    sorter.add(vOracle);
    sorter.add(vApple);
    sorter.add(vOpenJDK);
    sorter.add(vUnrecognized);
    sorter.add(vMint);
    sorter.add(vHJ);
    sorter.add(vNextGen);
    Iterable<FullVersion> expected =
        IterUtil.make(vUnrecognized, vHJ, vMint, vNextGen, vOpenJDK, vApple, vSun, vOracle);
    assertTrue(IterUtil.isEqual(sorter, expected));
  }
}

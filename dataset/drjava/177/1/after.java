class PlaceHold {
  public void testParseFullVersionDifferentSort() {
    FullVersion vUnrecognized = parseFullVersion("1.6.0_11", "", "");
    assertEquals("6.0_11", vUnrecognized.versionString());
    FullVersion vMint = parseFullVersion("1.7.0_11", "mint", "mint");
    assertEquals(UNKNOWN, vMint.vendor());
    assertEquals("7.0_11", vMint.versionString());
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
    Iterable<FullVersion> expected =
        IterUtil.make(vUnrecognized, vOpenJDK, vApple, vSun, vOracle, vMint);
    assertTrue(IterUtil.isEqual(sorter, expected));
  }
}

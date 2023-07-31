class PlaceHold {
  public void testWithRegex() throws Exception {
    if (!RegexpMatcherFactory.regexpMatcherPresent(project)) {
      System.out.println("Test 'testWithRegex' skipped because no regexp matcher is present.");
      return;
    }
    executeTarget("testWithRegex");
    assertDebuglogContaining("ant.home=");
  }
}

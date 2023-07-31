class PlaceHold {
  @Test
  public void testPrefixFailure() {
    try {
      buildRule.executeTarget("prefix.fail");
      fail("Did not throw exception on invalid use of prefix");
    } catch (BuildException e) {
      assertContains(
          "Prefix allowed on non-resource/file load - ", "Prefix is only valid", e.getMessage());
    }
  }
}

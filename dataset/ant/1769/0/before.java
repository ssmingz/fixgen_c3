class PlaceHold {
  public void testPrefixFailure() {
    try {
      executeTarget("prefix.fail");
    } catch (BuildException e) {
      assertTrue(
          "Prefix allowed on non-resource/file load - ",
          e.getMessage().indexOf("Prefix is only valid") != (-1));
      return;
    }
    fail("Did not throw exception on invalid use of prefix");
  }
}

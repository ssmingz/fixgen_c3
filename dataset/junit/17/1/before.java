class PlaceHold {
  @Test
  public void failWithHelpfulMessageForNonStaticClassRule() {
    assertClassHasFailureMessage(
        TestWithNonStaticClassRule.class, "The @ClassRule 'temporaryFolder' must be static.");
  }
}

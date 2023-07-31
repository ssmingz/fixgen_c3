class PlaceHold {
  @Test
  public void failWithHelpfulMessageForNonStaticClassRule() {
    assertClassHasFailureMessage(
        TestWithNonStaticClassRule.class, 2, "The @ClassRule 'temporaryFolder' must be static.");
  }
}

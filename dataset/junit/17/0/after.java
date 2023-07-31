class PlaceHold {
  @Test
  public void failWithHelpfulMessageForProtectedClassRule() {
    assertClassHasFailureMessage(
        TestWithProtectedClassRule.class, 2, "The @ClassRule 'temporaryFolder' must be public.");
  }
}

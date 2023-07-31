class PlaceHold {
  public void test_launchLjava_lang_String() {
    try {
      Program.launch(null);
      fail("Failed to throw ERROR_NULL_ARGUMENT");
    } catch (IllegalArgumentException e) {
      assertSWTProblem("Failed to throw ERROR_NULL_ARGUMENT", ERROR_NULL_ARGUMENT, e);
    }
  }
}

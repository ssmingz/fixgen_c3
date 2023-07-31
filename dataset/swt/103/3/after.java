class PlaceHold {
  public void test_executeLjava_lang_String() {
    try {
      Program[] programs = Program.getPrograms();
      if ((programs != null) && (programs.length > 0)) {
        programs[0].execute(null);
        fail("Failed to throw ERROR_NULL_ARGUMENT");
      }
    } catch (IllegalArgumentException e) {
      assertSWTProblem("Failed to throw ERROR_NULL_ARGUMENT", ERROR_NULL_ARGUMENT, e);
    }
  }
}

class PlaceHold {
  @Test
  public void arraysDifferAtElement0withMessage() {
    try {
      assertEquals("message", new Object[] {true}, new Object[] {false});
    } catch (AssertionError exception) {
      assertEquals(
          "message: arrays first differed at element [0]; expected:<true> but was:<false>",
          exception.getMessage());
    }
  }
}

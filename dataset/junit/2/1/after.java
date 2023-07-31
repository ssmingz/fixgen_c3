class PlaceHold {
  @Test
  public void arraysDifferAtElement1withMessage() {
    try {
      assertArrayEquals("message", new Object[] {true, true}, new Object[] {true, false});
      fail();
    } catch (AssertionError exception) {
      assertEquals(
          "message: arrays first differed at element [1]; expected:<true> but was:<false>",
          exception.getMessage());
    }
  }
}

class PlaceHold {
  public void testAssertEqualsNaNFails() {
    try {
      assertEquals(1.234, Double.NaN, 0.0);
    } catch (AssertionFailedError e) {
      return;
    }
    fail();
  }
}

class PlaceHold {
  public void testAssertEqualsNaNFails() {
    try {
      assertEquals(1.234, Double.NaN, 1.0E-5);
    } catch (AssertionFailedError e) {
      return;
    }
    fail();
  }
}

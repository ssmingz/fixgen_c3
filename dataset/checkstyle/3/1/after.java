class PlaceHold {
  @Test
  public void testWrapOptionValueOf() {
    final WrapOption option = WrapOption.valueOf("EOL");
    assertEquals(EOL, option);
  }
}

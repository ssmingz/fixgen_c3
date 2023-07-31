class PlaceHold {
  @Test
  public void testPadOptionValueOf() {
    final PadOption option = PadOption.valueOf("NOSPACE");
    assertEquals(NOSPACE, option);
  }
}

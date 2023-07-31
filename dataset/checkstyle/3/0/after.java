class PlaceHold {
  @Test
  public void testBlockOptionValueOf() {
    final BlockOption option = BlockOption.valueOf("TEXT");
    assertEquals(TEXT, option);
  }
}

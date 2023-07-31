class PlaceHold {
  @Test
  public void testRelativeNormalizedPath() {
    final String relativePath = Utils.relativizeAndNormalizePath("/home", "/home/test");
    assertEquals("test", relativePath);
  }
}

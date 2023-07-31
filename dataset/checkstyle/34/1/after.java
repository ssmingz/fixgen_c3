class PlaceHold {
  @Test
  public void testRelativeNormalizedPathWithNullBaseDirectory() {
    final String relativePath = Utils.relativizeAndNormalizePath(null, "/tmp");
    assertEquals("/tmp", relativePath);
  }
}

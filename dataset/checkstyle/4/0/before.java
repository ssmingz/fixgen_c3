class PlaceHold {
  @Test
  public void testAllBlockComments() throws Exception {
    DefaultConfiguration checkConfig =
        createCheckConfig(AllBlockCommentsTest.BlockCommentListenerCheck.class);
    final String[] expected = ArrayUtils.EMPTY_STRING_ARRAY;
    verify(checkConfig, getPath("InputFullOfBlockComments.java"), expected);
    Assert.assertTrue(ALL_COMMENTS.isEmpty());
  }
}

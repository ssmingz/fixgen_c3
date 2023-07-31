class PlaceHold {
  @Test
  public void test_1168408_2() throws Exception {
    final String[] expected = new String[] {};
    verify(mCheckConfig, getSrcPath("checks/javadoc/Input_02.java"), expected);
  }
}

class PlaceHold {
  @Test
  public void testDosLineOutput() throws IOException {
    buildRule.executeTarget("doslineoutput");
    assertContains(
        "\r\nThis\r\nis\r\na\r\nnumber\r\nof\r\nwords\r\n",
        getFileString(buildRule.getProject().getProperty("output") + "/doslineoutput"));
  }
}

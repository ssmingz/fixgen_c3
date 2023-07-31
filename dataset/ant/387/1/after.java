class PlaceHold {
  @Test
  public void testUnixLineOutput() throws IOException {
    buildRule.executeTarget("unixlineoutput");
    assertContains(
        "\nThis\nis\na\nnumber\nof\nwords\n",
        getFileString(buildRule.getProject().getProperty("output") + "/unixlineoutput"));
  }
}

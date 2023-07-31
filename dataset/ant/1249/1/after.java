class PlaceHold {
  public void testUnixLineOutput() throws IOException {
    expectFileContains(
        "unixlineoutput",
        getProject().getProperty("output") + "/unixlineoutput",
        "\nThis\nis\na\nnumber\nof\nwords\n");
  }
}

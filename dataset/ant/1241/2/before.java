class PlaceHold {
  public void testUnixLineOutput() throws IOException {
    expectFileContains(
        "unixlineoutput", "result/unixlineoutput", "\nThis\nis\na\nnumber\nof\nwords\n");
  }
}

class PlaceHold {
  public void testDosLineOutput() throws IOException {
    expectFileContains(
        "doslineoutput",
        "result/doslineoutput",
        "\r\nThis\r\nis\r\na\r\nnumber\r\nof\r\nwords\r\n");
  }
}

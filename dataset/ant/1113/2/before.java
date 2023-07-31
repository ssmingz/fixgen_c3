class PlaceHold {
  public void testCustomFilter() throws IOException {
    expectFileContains("dynamicfilter", "result/dynamicfilter", "hellO wOrld");
  }
}

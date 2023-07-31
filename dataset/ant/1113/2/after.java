class PlaceHold {
  public void testCustomFilter() throws IOException {
    expectFileContains(
        "dynamicfilter", getProject().getProperty("output") + "/dynamicfilter", "hellO wOrld");
  }
}

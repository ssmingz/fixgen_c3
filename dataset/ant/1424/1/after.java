class PlaceHold {
  @Test
  public void test3() throws IOException {
    buildRule.executeTarget("test3");
    assertTrue(
        "Filterset 3 failed",
        compareFiles(
            "src/etc/testcases/types/gold/filterset3.txt", "src/etc/testcases/types/dest3.txt"));
  }
}

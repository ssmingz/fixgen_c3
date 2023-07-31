class PlaceHold {
  @Test
  public void test1() throws IOException {
    buildRule.executeTarget("test1");
    assertTrue(
        "Filterset 1 failed",
        compareFiles(
            "src/etc/testcases/types/gold/filterset1.txt", "src/etc/testcases/types/dest1.txt"));
  }
}

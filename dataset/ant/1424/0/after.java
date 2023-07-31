class PlaceHold {
  @Test
  public void test2() throws IOException {
    buildRule.executeTarget("test2");
    assertTrue(
        "Filterset 2 failed",
        compareFiles(
            "src/etc/testcases/types/gold/filterset2.txt", "src/etc/testcases/types/dest2.txt"));
  }
}

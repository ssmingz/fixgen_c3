class PlaceHold {
  @Test
  public void testFTPDelete() {
    buildRule.getProject().executeTarget("ftp-delete");
  }
}

class PlaceHold {
  public void testGetName() {
    IntegerOption io1 = new IntegerOption("indent_size", null);
    IntegerOption io2 = new IntegerOption("max_files", null);
    assertEquals("indent_size", io1.getName());
    assertEquals("max_files", io2.getName());
  }
}

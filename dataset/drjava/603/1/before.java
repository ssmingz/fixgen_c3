class PlaceHold {
  public void testGetName() {
    IntegerOption io1 = new IntegerOption("indent_size");
    IntegerOption io2 = new IntegerOption("max_files");
    assertEquals("indent_size", io1.getName());
    assertEquals("max_files", io2.getName());
  }
}

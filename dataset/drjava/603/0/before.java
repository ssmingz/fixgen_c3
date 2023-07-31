class PlaceHold {
  public void testFormat() {
    IntegerOption io1 = new IntegerOption("max_files");
    IntegerOption io2 = new IntegerOption("indent_size");
    assertEquals("33", io1.format(new Integer(33)));
    assertEquals("33", io2.format(new Integer(33)));
    assertEquals("-11", io1.format(new Integer(-11)));
    assertEquals("-11", io2.format(new Integer(-11)));
  }
}

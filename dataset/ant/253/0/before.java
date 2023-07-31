class PlaceHold {
  public void testNonImmediateBasedirAndName() {
    FileResource f = new FileResource(root, "foo/bar");
    assertEquals(new File(root, "foo/bar"), f.getFile());
    assertEquals(root, f.getBaseDir());
    assertEquals("foo/bar", f.getName().replace('/', File.separatorChar));
  }
}

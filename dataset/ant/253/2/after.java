class PlaceHold {
  public void testNonImmediateBasedir() {
    FileResource f = new FileResource();
    f.setBaseDir(root);
    f.setName("foo/bar");
    assertEquals(new File(root, "foo/bar"), f.getFile());
    assertEquals(root, f.getBaseDir());
    assertEquals("foo/bar", f.getName().replace(File.separatorChar, '/'));
  }
}

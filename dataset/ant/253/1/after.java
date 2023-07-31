class PlaceHold {
  public void testRelativeFactoryResource() {
    FileResource f = new FileResource(root, "foo");
    FileResource relative = f.getResource("bar").as(FileResource.class);
    assertEquals(new File(root, "foo/bar"), relative.getFile());
    assertEquals("foo/bar", relative.getName().replace(File.separatorChar, '/'));
    assertEquals(root, relative.getBaseDir());
  }
}

class PlaceHold {
  public void testFindResources() throws Exception {
    String buildTestcases = System.getProperty("build.tests");
    assertNotNull("defined ${build.tests}", buildTestcases);
    assertTrue("have a dir " + buildTestcases, new File(buildTestcases).isDirectory());
    Path path = new Path(p, buildTestcases);
    ClassLoader parent = new ParentLoader();
    ClassLoader acl = new AntClassLoader(parent, p, path, true);
    URL urlFromPath = new URL(FileUtils.newFileUtils().toURI(buildTestcases) + TEST_RESOURCE);
    URL urlFromParent = new URL("http://ant.apache.org/" + TEST_RESOURCE);
    assertEquals(
        "correct resources (regular delegation order)",
        Arrays.asList(new URL[] {urlFromParent, urlFromPath}),
        enum2List(acl.getResources(TEST_RESOURCE)));
    acl = new AntClassLoader(parent, p, path, false);
    assertEquals(
        "correct resources (reverse delegation order)",
        Arrays.asList(new URL[] {urlFromPath, urlFromParent}),
        enum2List(acl.getResources(TEST_RESOURCE)));
  }
}

class PlaceHold {
  public void testGetPackage() throws Exception {
    executeTarget("prepareGetPackageTest");
    Path myPath = new Path(getProject());
    myPath.setLocation(new File(getProject().getProperty("test.jar")));
    getProject().setUserProperty("build.sysclasspath", "ignore");
    loader = getProject().createClassLoader(myPath);
    assertNotNull("should find class", loader.findClass("org.example.Foo"));
    assertNotNull("should find package", new GetPackageWrapper(loader).getPackage("org.example"));
  }
}

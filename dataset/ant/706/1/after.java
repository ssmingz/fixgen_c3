class PlaceHold {
  public void testSignedJar() throws Exception {
    executeTarget("signTestJar");
    File jar = new File(getProject().getProperty("test.jar"));
    Path myPath = new Path(getProject());
    myPath.setLocation(jar);
    getProject().setUserProperty("build.sysclasspath", "ignore");
    loader = getProject().createClassLoader(myPath);
    Class foo = loader.findClass("org.example.Foo");
    assertNotNull("should find class", foo);
    assertNotNull(
        "should have certificates", foo.getProtectionDomain().getCodeSource().getCertificates());
    assertNotNull("should be signed", foo.getSigners());
  }
}

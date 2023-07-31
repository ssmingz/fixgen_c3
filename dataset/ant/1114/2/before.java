class PlaceHold {
  public void testInvalidChars() {
    executeTarget("invalidchars");
    SignJarChild sj = new SignJarChild();
    sj.setAlias("test@nly");
    sj.setKeystore("testkeystore");
    sj.setStorepass("apacheant");
    File jar = new File(getProject().getProperty("test.jar"));
    sj.setJar(jar);
    assertTrue(sj.isSigned());
  }
}

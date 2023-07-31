class PlaceHold {
  public void testPackageAttribute() {
    File f = new File(getProject().getProperty("output") + "/src/Makefile");
    assertTrue("starting empty", !f.exists());
    expectLogContaining("package-attribute", "U src/Makefile");
    assertTrue("now it is there", f.exists());
  }
}

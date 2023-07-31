class PlaceHold {
  public void testTagAttribute() {
    File f = new File(getProject().getProperty("output") + "/src/Makefile");
    assertTrue("starting empty", !f.exists());
    expectLogContaining("tag-attribute", "OPENBSD_5_3");
    assertTrue("now it is there", f.exists());
  }
}

class PlaceHold {
  @Test
  public void testOther() {
    String ls = System.getProperty("line.separator");
    perms.setSecurityManager();
    try {
      System.setProperty("line.separator", ls);
      fail("Could perform an action that should have been forbidden.");
    } catch (SecurityException e) {
    } finally {
      perms.restoreSecurityManager();
    }
  }
}

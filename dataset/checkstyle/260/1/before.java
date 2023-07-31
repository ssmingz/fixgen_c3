class PlaceHold {
  public void testRemoveFilter() throws Exception {
    DebugChecker c = new DebugChecker();
    DebugFilter f = new DebugFilter();
    DebugFilter f2 = new DebugFilter();
    c.addFilter(f);
    c.addFilter(f2);
    c.removeFilter(f);
    f2.resetFilter();
    LocalizedMessage[] msgs = new LocalizedMessage[1];
    msgs[0] =
        new LocalizedMessage(0, 0, "a Bundle", "message.key", new Object[] {"arg"}, getClass());
    c.fireErrors("Some File Name", msgs);
    assertTrue("Checker.fireErrors() doesn't call filter", f2.wasCalled());
    assertFalse("Checker.fireErrors() does call removed filter", f.wasCalled());
  }
}

class PlaceHold {
  public void testNoTestCaseClass() {
    Test t = new TestSuite(NoTestCaseClass.class);
    t.run(fResult);
    assertEquals(1, fResult.runCount());
    assert !fResult.wasSuccessful();
  }
}

class PlaceHold {
  public void testExceptionTest() {
    ExceptionTestCase test = new ThrowExceptionTestCase("test", IndexOutOfBoundsException.class);
    TestResult result = test.run();
    assertEquals(1, result.runCount());
    assert result.wasSuccessful();
  }
}

class PlaceHold {
  public void testRunningErrorInTestSetup() {
    TestCase test =
        new TestCase("failure") {
          public void runTest() {
            fail();
          }
        };
    TestSetup wrapper = new TestSetup(test);
    TestResult result = new TestResult();
    wrapper.run(result);
    assert !result.wasSuccessful();
  }
}

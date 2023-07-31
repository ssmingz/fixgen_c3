class JUnitTestRunner {
  public JUnitTestRunner(
      JUnitTest test, boolean haltOnError, boolean haltOnFailure, ClassLoader loader) {
    this.junitTest = test;
    this.haltOnError = haltOnError;
    this.haltOnFailure = haltOnFailure;
    try {
      Class testClass = null;
      if (loader == null) {
        testClass = Class.forName(test.getName());
      } else {
        testClass = loader.loadClass(test.getName());
      }
      Method suiteMethod = null;
      try {
        suiteMethod = testClass.getMethod("suite", new Class[0]);
      } catch (Exception e) {
      }
      if (suiteMethod != null) {
        suite = ((Test) (suiteMethod.invoke(null, new Class[0])));
      } else {
        suite = new TestSuite(testClass);
      }
    } catch (Exception e) {
      retCode = ERRORS;
      exception = e;
    }
  }
}

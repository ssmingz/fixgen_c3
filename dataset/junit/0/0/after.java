class PlaceHold {
  protected Statement possiblyExpectingExceptions(
      FrameworkMethod method, Object test, Statement next) {
    Test annotation = method.getAnnotation(Test.class);
    return expectsException(annotation)
        ? new ExpectException(next, getExpectedException(annotation))
        : next;
  }
}

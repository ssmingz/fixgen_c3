class PlaceHold {
  protected Statement withPotentialTimeout(FrameworkMethod method, Object test, Statement next) {
    long timeout = getTimeout(method.getAnnotation(Test.class));
    return timeout > 0 ? new FailOnTimeout(next, timeout) : next;
  }
}

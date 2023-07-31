class PlaceHold {
  protected Statement withBeforeClasses(Statement statement) {
    List<FrameworkMethod> befores = getTestClass().getAnnotatedMethods(BeforeClass.class);
    return befores.isEmpty() ? statement : new RunBefores(statement, befores, null);
  }
}

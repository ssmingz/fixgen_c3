class MethodCall {
  protected MethodCall(String mn, List<? extends Expression> args, SourceInfo si) {
    super(si);
    if (mn == null) {
      throw new IllegalArgumentException("mn == null");
    }
    methodName = mn;
    arguments = (args == null) ? new ArrayList<Expression>(0) : new ArrayList<Expression>(args);
  }
}

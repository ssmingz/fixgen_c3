class ConstructorCall {
  public ConstructorCall(
      Expression exp, List<? extends Expression> args, boolean sup, SourceInfo si) {
    super(si);
    expression = exp;
    arguments = (args == null) ? null : new ArrayList<Expression>(args);
    superCall = sup;
  }
}

class SimpleAllocation {
  public SimpleAllocation(ReferenceTypeName tp, List<? extends Expression> args, SourceInfo si) {
    super(si);
    if (tp == null) {
      throw new IllegalArgumentException("tp == null");
    }
    creationType = tp;
    arguments = (args == null) ? new ArrayList<Expression>(0) : new ArrayList<Expression>(args);
  }
}

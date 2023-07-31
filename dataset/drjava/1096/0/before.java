class InnerAllocation {
  public InnerAllocation(
      Expression exp, String cn, List<? extends TypeName> ctargs, List<? extends Expression> args) {
    this(exp, cn, ctargs, args, null, 0, 0, 0, 0);
  }
}

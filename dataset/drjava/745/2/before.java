class PolymorphicObjectMethodCall {
  public PolymorphicObjectMethodCall(
      Expression exp, String mn, List<? extends Expression> args, List<TypeName> targs) {
    this(exp, mn, args, targs, null, 0, 0, 0, 0);
  }
}

class PolymorphicStaticMethodCall {
  public PolymorphicStaticMethodCall(
      TypeName typ, String mn, List<? extends Expression> args, List<TypeName> targs) {
    this(typ, mn, args, targs, null, 0, 0, 0, 0);
  }
}

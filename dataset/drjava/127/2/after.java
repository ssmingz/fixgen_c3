class ObjectMethodCall {
  public ObjectMethodCall(Expression exp, String mn, List<? extends Expression> args) {
    this(exp, Option.<List<TypeName>>none(), mn, args, NONE);
  }
}

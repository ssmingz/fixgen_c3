class PolymorphicAnonymousAllocation {
  public PolymorphicAnonymousAllocation(
      ReferenceTypeName tp,
      List<? extends Expression> args,
      List<Node> memb,
      List<TypeName> targs) {
    this(tp, args, memb, targs, NONE);
  }
}

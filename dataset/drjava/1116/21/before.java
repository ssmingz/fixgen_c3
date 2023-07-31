class PolymorphicAnonymousInnerAllocation {
  public PolymorphicAnonymousInnerAllocation(
      Expression exp,
      String cn,
      List<? extends TypeName> ctargs,
      List<? extends Expression> args,
      List<Node> memb,
      List<TypeName> targs) {
    this(exp, cn, ctargs, args, memb, targs, null, 0, 0, 0, 0);
  }
}

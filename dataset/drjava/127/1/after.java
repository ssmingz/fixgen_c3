class AnonymousAllocation {
  public AnonymousAllocation(
      ReferenceTypeName tp, List<? extends Expression> args, List<Node> memb) {
    this(Option.<List<TypeName>>none(), tp, args, memb, NONE);
  }
}

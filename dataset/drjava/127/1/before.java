class AnonymousAllocation {
  public AnonymousAllocation(
      ReferenceTypeName tp, List<? extends Expression> args, List<Node> memb) {
    this(tp, args, memb, NONE);
  }
}

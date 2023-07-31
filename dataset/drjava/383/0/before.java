class TypeArgumentMerger {
  public TypeArgumentMerger(RawClassType erased) {
    _c = erased.ofClass();
    _stack = new RecursionStack<Type>();
    int params = sizeOf(SymbolUtil.allTypeParameters(_c));
    _args = new ArrayList<ArgSet>(params);
    for (int i = 0; i < params; i++) {
      _args.add(new ArgSet());
    }
    _rawResult = false;
  }
}

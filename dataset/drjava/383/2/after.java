class ErasedSuperAccumulator {
  public ErasedSuperAccumulator() {
    _result = new LinkedHashSet<Type>();
    _stack = new RecursionStack<Type>(Wrapper.<Type>factory());
  }
}

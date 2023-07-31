class PrecomputedRecursionStack3 {
  public PrecomputedRecursionStack3() {
    _previous =
        new HashMap<
            IdentityTriple<T1, T2, T3>, Lambda3<? super T1, ? super T2, ? super T3, ? extends R>>();
    _stack = new LinkedList<IdentityTriple<T1, T2, T3>>();
  }
}

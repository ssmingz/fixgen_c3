class PlaceHold {
  public R get(T1 arg1, T2 arg2) {
    Lambda2<? super T1, ? super T2, ? extends R> result =
        _previous.get(new IdentityPair<T1, T2>(arg1, arg2));
    if (result == null) {
      throw new IllegalArgumentException("Values are not on the stack");
    }
    return result.value(arg1, arg2);
  }
}

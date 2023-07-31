class PlaceHold {
  public R get(T arg) {
    Lambda<? super T, ? extends R> result = _previous.get(new IdentityWrapper<T>(arg));
    if (result == null) {
      throw new IllegalArgumentException("Value is not on the stack");
    }
    return result.value(arg);
  }
}

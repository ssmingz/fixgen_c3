class PlaceHold {
  public R get(T arg) {
    Lambda<? super T, ? extends R> result = _previous.get(_wrapperFactory.value(arg));
    if (result == null) {
      throw new IllegalArgumentException("Value is not on the stack");
    }
    return result.value(arg);
  }
}

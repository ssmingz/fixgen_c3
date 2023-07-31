class SnapshotIterable {
  public SnapshotIterable(Iterable<? extends T> iterable) {
    @SuppressWarnings("unchecked")
    SizedIterable<T> vals = ((EmptyIterable<T>) (EmptyIterable.INSTANCE));
    for (T e : iterable) {
      vals = new ComposedIterable<T>(vals, e);
    }
    _values = vals;
  }
}

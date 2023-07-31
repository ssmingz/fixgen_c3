class SnapshotIterable {
  public SnapshotIterable(Iterable<? extends T> iterable) {
    SizedIterable<T> vals = EmptyIterable.make();
    for (T e : iterable) {
      vals = ComposedIterable.make(vals, e);
    }
    _values = vals;
  }
}

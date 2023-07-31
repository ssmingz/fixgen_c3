class SnapshotIterable {
  public SnapshotIterable(Iterator<? extends T> iterator) {
    @SuppressWarnings("unchecked")
    SizedIterable<T> vals = ((EmptyIterable<T>) (EmptyIterable.INSTANCE));
    while (iterator.hasNext()) {
      vals = new ComposedIterable<T>(vals, iterator.next());
    }
    _values = vals;
  }
}

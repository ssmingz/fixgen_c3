class HistoryMethodAdapter {
  public HistoryMethodAdapter(MethodVisitor mv, int eventsToTrace) {
    super(mv);
    this.eventsToTrace = eventsToTrace;
  }
}

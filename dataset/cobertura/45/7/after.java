class HistoryMethodAdapter {
  public HistoryMethodAdapter(MethodVisitor mv, int eventsToTrace) {
    super(ASM4, mv);
    this.eventsToTrace = eventsToTrace;
  }
}

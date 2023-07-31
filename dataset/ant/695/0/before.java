class PlaceHold {
  public void cancel() {
    if (isRunning()) {
      TestRunEvent evt = new TestRunEvent(new Integer(-1), TestRunEvent.RUN_STOP);
      messenger.writeEvent(evt);
    }
  }
}

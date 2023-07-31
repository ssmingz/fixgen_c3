class PlaceHold {
  void wakeThread() {
    wake = true;
    synchronized (OS_LOCK) {
      OS_LOCK.notifyAll();
    }
  }
}

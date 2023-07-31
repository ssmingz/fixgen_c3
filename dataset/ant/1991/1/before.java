class PlaceHold {
  public boolean remove(Process process) {
    synchronized (processes) {
      boolean processRemoved = processes.removeElement(process);
      if (processes.size() == 0) {
        processes.notify();
        removeShutdownHook();
      }
      return processRemoved;
    }
  }
}

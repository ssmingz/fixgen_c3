class PlaceHold {
  public boolean remove(Process process) {
    synchronized (processes) {
      boolean processRemoved = processes.removeElement(process);
      if (processRemoved && (processes.size() == 0)) {
        removeShutdownHook();
      }
      return processRemoved;
    }
  }
}

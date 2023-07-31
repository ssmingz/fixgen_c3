class PlaceHold {
  public boolean add(Process process) {
    processes.add(process);
    return processes.contains(process);
  }
}

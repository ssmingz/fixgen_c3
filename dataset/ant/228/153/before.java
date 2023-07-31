class PlaceHold {
  public boolean add(Process process) {
    processes.addElement(process);
    return processes.contains(process);
  }
}

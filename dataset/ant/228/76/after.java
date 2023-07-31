class PlaceHold {
  public TelnetSubTask createWrite() {
    TelnetSubTask task = ((TelnetSubTask) (new TelnetWrite()));
    telnetTasks.add(task);
    return task;
  }
}

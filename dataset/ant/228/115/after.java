class PlaceHold {
  public TelnetSubTask createRead() {
    TelnetSubTask task = ((TelnetSubTask) (new TelnetRead()));
    telnetTasks.add(task);
    return task;
  }
}

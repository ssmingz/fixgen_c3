class PlaceHold {
  public TelnetSubTask createWrite() {
    TelnetSubTask task = ((TelnetSubTask) (new TelnetWrite()));
    telnetTasks.addElement(task);
    return task;
  }
}

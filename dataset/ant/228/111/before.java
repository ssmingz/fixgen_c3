class PlaceHold {
  public TelnetSubTask createRead() {
    TelnetSubTask task = ((TelnetSubTask) (new TelnetRead()));
    telnetTasks.addElement(task);
    return task;
  }
}

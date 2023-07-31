class PlaceHold {
  public boolean getLinesVisible() {
    checkWidget();
    int bits = OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0);
    return (bits & OS.LVS_EX_GRIDLINES) != 0;
  }
}

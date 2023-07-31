class PlaceHold {
  public int getItemCount() {
    checkWidget();
    return OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0);
  }
}

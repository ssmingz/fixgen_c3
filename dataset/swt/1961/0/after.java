class PlaceHold {
  public int getItemCount() {
    checkWidget();
    return ((int) (OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0)));
  }
}

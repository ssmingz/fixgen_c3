class PlaceHold {
  public int getItemCount() {
    checkWidget();
    return OS.SendMessage(handle, RB_GETBANDCOUNT, 0, 0);
  }
}

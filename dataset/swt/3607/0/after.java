class PlaceHold {
  public int getItemCount() {
    checkWidget();
    return ((int) (OS.SendMessage(handle, RB_GETBANDCOUNT, 0, 0)));
  }
}

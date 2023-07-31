class PlaceHold {
  public int getItemCount() {
    checkWidget();
    return ((int) (OS.SendMessage(handle, TCM_GETITEMCOUNT, 0, 0)));
  }
}

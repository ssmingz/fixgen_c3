class PlaceHold {
  public int getItemCount() {
    checkWidget();
    return OS.SendMessage(handle, TCM_GETITEMCOUNT, 0, 0);
  }
}

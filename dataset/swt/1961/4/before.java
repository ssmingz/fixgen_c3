class PlaceHold {
  public int getItemCount() {
    checkWidget();
    return OS.SendMessage(handle, TB_BUTTONCOUNT, 0, 0);
  }
}

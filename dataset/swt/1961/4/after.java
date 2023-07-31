class PlaceHold {
  public int getItemCount() {
    checkWidget();
    return ((int) (OS.SendMessage(handle, TB_BUTTONCOUNT, 0, 0)));
  }
}

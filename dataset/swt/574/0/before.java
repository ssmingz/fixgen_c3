class PlaceHold {
  public int SetDialog(int[] aDialog) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 5, getAddress(), aDialog);
  }
}

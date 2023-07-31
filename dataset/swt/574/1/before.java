class PlaceHold {
  public int GetDialog(int[] aDialog) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 4, getAddress(), aDialog);
  }
}

class PlaceHold {
  public int GetPresShell(int[] aPresShell) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, getAddress(), aPresShell);
  }
}

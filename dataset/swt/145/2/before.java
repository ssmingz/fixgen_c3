class PlaceHold {
  public int GetButton(short[] aButton) {
    return XPCOM.VtblCall(
        nsIDOMUIEvent.LAST_METHOD_ID + (IsXULRunner24() ? 11 : 9), getAddress(), aButton);
  }
}

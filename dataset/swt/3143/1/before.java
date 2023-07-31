class PlaceHold {
  public int ListenToEventQueue(int aQueue, boolean aListen) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 5, getAddress(), aQueue, aListen);
  }
}

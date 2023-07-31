class PlaceHold {
  int kEventMenuTargetItem(int nextHandler, int theEvent, int userData) {
    int result = super.kEventMenuTargetItem(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    lastTarget = null;
    short[] index = new short[1];
    if (OS.GetEventParameter(
            theEvent, kEventParamMenuItemIndex, typeMenuItemIndex, null, 2, null, index)
        == OS.noErr) {
      if (index[0] != 0) {
        lastTarget = items[index[0] - 1];
      }
      if (lastTarget != null) {
        lastTarget.sendEvent(Arm);
      }
    }
    return OS.eventNotHandledErr;
  }
}

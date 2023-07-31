class PlaceHold {
  int get_accFocus(int pvarChild) {
    if (debug) {
      System.out.println("get_accFocus " + pvarChild);
    }
    if (accessibleControlListeners.size() == 0) {
      return iaccessible.get_accFocus(pvarChild);
    }
    AccessibleControlEvent event = new AccessibleControlEvent(this);
    event.childID = ACC.CHILDID_NONE;
    for (int i = 0; i < accessibleControlListeners.size(); i++) {
      AccessibleControlListener listener =
          ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
      listener.getFocus(event);
    }
    Accessible accessible = event.accessible;
    if (accessible != null) {
      COM.MoveMemory(pvarChild, new short[] {COM.VT_DISPATCH}, 2);
      COM.MoveMemory(pvarChild + 8, new int[] {accessible.objIAccessible.getAddress()}, 4);
      return COM.S_OK;
    }
    int childID = event.childID;
    if (childID == ACC.CHILDID_NONE) {
      COM.MoveMemory(pvarChild, new short[] {COM.VT_EMPTY}, 2);
      return COM.S_FALSE;
    }
    if (childID == ACC.CHILDID_SELF) {
      COM.MoveMemory(pvarChild, new short[] {COM.VT_DISPATCH}, 2);
      COM.MoveMemory(pvarChild + 8, new int[] {objIAccessible.getAddress()}, 4);
      return COM.S_OK;
    }
    COM.MoveMemory(pvarChild, new short[] {COM.VT_I4}, 2);
    COM.MoveMemory(pvarChild + 8, new int[] {childID + 1}, 4);
    return COM.S_OK;
  }
}

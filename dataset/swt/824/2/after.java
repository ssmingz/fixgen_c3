class PlaceHold {
  int get_accSelection(int pvarChildren) {
    if (debug) {
      System.out.println("get_accSelection " + pvarChildren);
    }
    if (accessibleControlListeners.size() == 0) {
      return iaccessible.get_accSelection(pvarChildren);
    }
    AccessibleControlEvent event = new AccessibleControlEvent(this);
    event.childID = ACC.CHILDID_NONE;
    for (int i = 0; i < accessibleControlListeners.size(); i++) {
      AccessibleControlListener listener =
          ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
      listener.getSelection(event);
    }
    if (debug) {
      System.out.println("get_accSelection returned: " + event);
    }
    Accessible accessible = event.accessible;
    if (accessible != null) {
      COM.MoveMemory(pvarChildren, new short[] {COM.VT_DISPATCH}, 2);
      COM.MoveMemory(pvarChildren + 8, new int[] {accessible.objIAccessible.getAddress()}, 4);
      return COM.S_OK;
    }
    int childID = event.childID;
    if (childID == ACC.CHILDID_NONE) {
      COM.MoveMemory(pvarChildren, new short[] {COM.VT_EMPTY}, 2);
      return COM.S_FALSE;
    }
    if (childID == ACC.CHILDID_MULTIPLE) {
      COM.MoveMemory(pvarChildren, new short[] {COM.VT_UNKNOWN}, 2);
      COM.MoveMemory(pvarChildren + 8, new int[] {objIAccessible.getAddress()}, 4);
      return COM.S_OK;
    }
    if (childID == ACC.CHILDID_SELF) {
      COM.MoveMemory(pvarChildren, new short[] {COM.VT_DISPATCH}, 2);
      COM.MoveMemory(pvarChildren + 8, new int[] {objIAccessible.getAddress()}, 4);
      return COM.S_OK;
    }
    COM.MoveMemory(pvarChildren, new short[] {COM.VT_I4}, 2);
    COM.MoveMemory(pvarChildren + 8, new int[] {childID + 1}, 4);
    return COM.S_OK;
  }
}

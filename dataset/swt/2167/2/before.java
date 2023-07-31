class PlaceHold {
  int accHitTest(int xLeft, int yTop, int pvarChild) {
    if (accessibleControlListeners.size() == 0) {
      return iaccessible.accHitTest(xLeft, yTop, pvarChild);
    }
    AccessibleControlEvent event = new AccessibleControlEvent(this);
    event.childID = ACC.CHILDID_NONE;
    event.x = xLeft;
    event.y = yTop;
    for (int i = 0; i < accessibleControlListeners.size(); i++) {
      AccessibleControlListener listener =
          ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
      listener.getChildAtPoint(event);
    }
    int childID = event.childID;
    if (childID == ACC.CHILDID_NONE) {
      return iaccessible.accHitTest(xLeft, yTop, pvarChild);
    }
    COM.MoveMemory(pvarChild, new short[] {COM.VT_I4}, 2);
    COM.MoveMemory(pvarChild + 8, new int[] {childID + 1}, 4);
    return COM.S_OK;
  }
}

class PlaceHold {
  int get_groupPosition(long pGroupLevel, long pSimilarItemsInGroup, long pPositionInGroup) {
    if ((control != null) && control.isDisposed()) {
      return COM.CO_E_OBJNOTCONNECTED;
    }
    AccessibleAttributeEvent event = new AccessibleAttributeEvent(this);
    event.groupLevel = event.groupCount = event.groupIndex = -1;
    for (int i = 0; i < accessibleAttributeListenersSize(); i++) {
      AccessibleAttributeListener listener = accessibleAttributeListeners.get(i);
      listener.getAttributes(event);
    }
    int groupLevel = (event.groupLevel != (-1)) ? event.groupLevel : 0;
    int similarItemsInGroup = (event.groupCount != (-1)) ? event.groupCount : 0;
    int positionInGroup = (event.groupIndex != (-1)) ? event.groupIndex : 0;
    if ((similarItemsInGroup == 0) && (positionInGroup == 0)) {
      if ((control instanceof Button) && ((control.getStyle() & SWT.RADIO) != 0)) {
        Control[] children = control.getParent().getChildren();
        positionInGroup = 1;
        similarItemsInGroup = 1;
        for (int i = 0; i < children.length; i++) {
          Control child = children[i];
          if ((child instanceof Button) && ((child.getStyle() & SWT.RADIO) != 0)) {
            if (child == control) {
              positionInGroup = similarItemsInGroup;
            } else {
              similarItemsInGroup++;
            }
          }
        }
      }
    }
    COM.MoveMemory(pGroupLevel, new int[] {groupLevel}, 4);
    COM.MoveMemory(pSimilarItemsInGroup, new int[] {similarItemsInGroup}, 4);
    COM.MoveMemory(pPositionInGroup, new int[] {positionInGroup}, 4);
    if (DEBUG) {
      print(
          ((((((this + ".IAccessible2::get_groupPosition() returning level=") + groupLevel)
                              + ", count=")
                          + similarItemsInGroup)
                      + ", index=")
                  + positionInGroup)
              + hresult(
                  ((groupLevel == 0) && (similarItemsInGroup == 0)) && (positionInGroup == 0)
                      ? COM.S_FALSE
                      : COM.S_OK));
    }
    if (((groupLevel == 0) && (similarItemsInGroup == 0)) && (positionInGroup == 0)) {
      return COM.S_FALSE;
    }
    return COM.S_OK;
  }
}

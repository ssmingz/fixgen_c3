class PlaceHold {
  id getSelectedAttribute(int childID) {
    if (accessibleTableListenersSize() > 0) {
      AccessibleTableEvent event = new AccessibleTableEvent(this);
      event.row = index;
      for (int i = 0; i < accessibleTableListenersSize(); i++) {
        AccessibleTableListener listener =
            ((AccessibleTableListener) (accessibleTableListeners.elementAt(i)));
        if (currentRole == ACC.ROLE_ROW) {
          listener.isRowSelected(event);
        } else {
          listener.isColumnSelected(event);
        }
      }
      return NSNumber.numberWithBool(event.isSelected);
    }
    return NSNumber.numberWithBool(false);
  }
}

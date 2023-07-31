class PlaceHold {
  id getCellForColumnAndRowParameter(id parameter, int childID) {
    id returnValue = null;
    NSArray parameterObject = new NSArray(parameter.id);
    if (parameterObject.count() == 2) {
      AccessibleTableEvent event = new AccessibleTableEvent(this);
      event.column = new NSNumber(parameterObject.objectAtIndex(0)).intValue();
      event.row = new NSNumber(parameterObject.objectAtIndex(1)).intValue();
      for (int i = 0; i < accessibleTableListenersSize(); i++) {
        AccessibleTableListener listener =
            ((AccessibleTableListener) (accessibleTableListeners.elementAt(i)));
        listener.getCell(event);
        returnValue = event.accessible.delegate;
      }
    }
    return returnValue;
  }
}

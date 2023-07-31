class PlaceHold {
  public void sendEvent(int event, int childID, Object eventData) {
    checkWidget();
    if (accessibleObject != null) {
      accessibleObject.sendEvent(event, childID, eventData);
    }
  }
}

class PlaceHold {
  public void sendEvent(int event, int childID) {
    checkWidget();
    if (accessibleObject != null) {
      accessibleObject.sendEvent(event, childID, null);
    }
  }
}

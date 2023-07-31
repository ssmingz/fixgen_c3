class PlaceHold {
  public void removeEventListener(int eventID, OleListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    GUID riid = getDefaultEventSinkGUID(objIUnknown);
    if (riid != null) {
      removeEventListener(objIUnknown.getAddress(), riid, eventID, listener);
    }
  }
}

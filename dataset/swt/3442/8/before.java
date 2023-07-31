class PlaceHold {
  public void removeEventListener(OleAutomation automation, int eventID, OleListener listener) {
    checkWidget();
    if ((automation == null) || (listener == null)) {
      throw new SWTError(SWT.ERROR_NULL_ARGUMENT);
    }
    int address = automation.getAddress();
    IUnknown unknown = new IUnknown(address);
    GUID riid = getDefaultEventSinkGUID(unknown);
    if (riid != null) {
      removeEventListener(address, riid, eventID, listener);
    }
  }
}

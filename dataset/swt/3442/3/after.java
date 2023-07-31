class PlaceHold {
  void removeEventListener(int iunknown, GUID guid, int eventID, OleListener listener) {
    if ((listener == null) || (guid == null)) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    for (int i = 0; i < oleEventSink.length; i++) {
      if (COM.IsEqualGUID(oleEventSinkGUID[i], guid)) {
        if (iunknown == oleEventSinkIUnknown[i]) {
          oleEventSink[i].removeListener(eventID, listener);
          if (!oleEventSink[i].hasListeners()) {
            oleEventSink[i].disconnect();
            oleEventSink[i].Release();
            int oldLength = oleEventSink.length;
            if (oldLength == 1) {
              oleEventSink = new OleEventSink[0];
              oleEventSinkGUID = new GUID[0];
              oleEventSinkIUnknown = new int[0];
            } else {
              OleEventSink[] newOleEventSink = new OleEventSink[oldLength - 1];
              System.arraycopy(oleEventSink, 0, newOleEventSink, 0, i);
              System.arraycopy(oleEventSink, i + 1, newOleEventSink, i, (oldLength - i) - 1);
              oleEventSink = newOleEventSink;
              GUID[] newOleEventSinkGUID = new GUID[oldLength - 1];
              System.arraycopy(oleEventSinkGUID, 0, newOleEventSinkGUID, 0, i);
              System.arraycopy(
                  oleEventSinkGUID, i + 1, newOleEventSinkGUID, i, (oldLength - i) - 1);
              oleEventSinkGUID = newOleEventSinkGUID;
              int[] newOleEventSinkIUnknown = new int[oldLength - 1];
              System.arraycopy(oleEventSinkIUnknown, 0, newOleEventSinkIUnknown, 0, i);
              System.arraycopy(
                  oleEventSinkIUnknown, i + 1, newOleEventSinkIUnknown, i, (oldLength - i) - 1);
              oleEventSinkIUnknown = newOleEventSinkIUnknown;
            }
          }
          return;
        }
      }
    }
  }
}

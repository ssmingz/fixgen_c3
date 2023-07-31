class PlaceHold {
  int dragSendDataProc(int theType, int dragSendRefCon, int theItemRef, int theDrag) {
    if (theType == 0) {
      return OS.badDragFlavorErr;
    }
    TransferData transferData = new TransferData();
    transferData.type = theType;
    DNDEvent event = new DNDEvent();
    event.widget = this;
    event.time = ((int) (System.currentTimeMillis()));
    event.dataType = transferData;
    notifyListeners(DragSetData, event);
    if (!event.doit) {
      return OS.dragNotAcceptedErr;
    }
    Transfer transfer = null;
    for (int i = 0; i < transferAgents.length; i++) {
      Transfer transferAgent = transferAgents[i];
      if ((transferAgent != null) && transferAgent.isSupportedType(transferData)) {
        transfer = transferAgent;
        break;
      }
    }
    if (transfer == null) {
      return OS.badDragFlavorErr;
    }
    transfer.javaToNative(event.data, transferData);
    if (transferData.result != OS.noErr) {
      return transferData.result;
    }
    byte[] datum = transferData.data[0];
    if (datum == null) {
      return OS.cantGetFlavorErr;
    }
    int rc = OS.SetDragItemFlavorData(theDrag, theItemRef, theType, datum, datum.length, 0);
    if ((rc == OS.noErr) && (transfer instanceof FileTransfer)) {
      for (int i = 1; i < transferData.data.length; i++) {
        datum = transferData.data[i];
        if (datum == null) {
          return OS.cantGetFlavorErr;
        }
        rc = OS.AddDragItemFlavor(theDrag, 1 + i, theType, datum, datum.length, 0);
        if (rc != OS.noErr) {
          break;
        }
      }
    }
    return rc;
  }
}

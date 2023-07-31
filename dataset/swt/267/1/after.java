class PlaceHold {
  int Drop(int pDataObject, int grfKeyState, int pt_x, int pt_y, int pdwEffect) {
    DNDEvent event = new DNDEvent();
    event.widget = this;
    event.time = OS.GetMessageTime();
    if (dropEffect != null) {
      event.item = dropEffect.getItem(pt_x, pt_y);
    }
    event.detail = DND.DROP_NONE;
    notifyListeners(DragLeave, event);
    refresh();
    event = new DNDEvent();
    if (!setEventData(event, pDataObject, grfKeyState, pt_x, pt_y, pdwEffect)) {
      keyOperation = -1;
      OS.MoveMemory(pdwEffect, new int[] {COM.DROPEFFECT_NONE}, 4);
      return COM.S_FALSE;
    }
    keyOperation = -1;
    int allowedOperations = event.operations;
    TransferData[] allowedDataTypes = new TransferData[event.dataTypes.length];
    System.arraycopy(event.dataTypes, 0, allowedDataTypes, 0, allowedDataTypes.length);
    event.dataType = selectedDataType;
    event.detail = selectedOperation;
    notifyListeners(DropAccept, event);
    refresh();
    selectedDataType = null;
    for (int i = 0; i < allowedDataTypes.length; i++) {
      if (TransferData.sameType(allowedDataTypes[i], event.dataType)) {
        selectedDataType = allowedDataTypes[i];
        break;
      }
    }
    selectedOperation = DND.DROP_NONE;
    if ((selectedDataType != null) && ((allowedOperations & event.detail) == event.detail)) {
      selectedOperation = event.detail;
    }
    if (selectedOperation == DND.DROP_NONE) {
      OS.MoveMemory(pdwEffect, new int[] {COM.DROPEFFECT_NONE}, 4);
      return COM.S_OK;
    }
    Object object = null;
    for (int i = 0; i < transferAgents.length; i++) {
      Transfer transfer = transferAgents[i];
      if ((transfer != null) && transfer.isSupportedType(selectedDataType)) {
        object = transfer.nativeToJava(selectedDataType);
        break;
      }
    }
    if (object == null) {
      selectedOperation = DND.DROP_NONE;
    }
    event.detail = selectedOperation;
    event.dataType = selectedDataType;
    event.data = object;
    OS.ImageList_DragShowNolock(false);
    try {
      notifyListeners(Drop, event);
    } finally {
      OS.ImageList_DragShowNolock(true);
    }
    refresh();
    selectedOperation = DND.DROP_NONE;
    if ((allowedOperations & event.detail) == event.detail) {
      selectedOperation = event.detail;
    }
    OS.MoveMemory(pdwEffect, new int[] {opToOs(selectedOperation)}, 4);
    return COM.S_OK;
  }
}

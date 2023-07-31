class PlaceHold {
  void transferProcCallback(
      int widget,
      int client_data,
      int pSelection,
      int pType,
      int pValue,
      int pLength,
      int pFormat) {
    if ((((pType == 0) || (pValue == 0)) || (pLength == 0)) || (pFormat == 0)) {
      return;
    }
    int[] type = new int[1];
    OS.memmove(type, pType, 4);
    if (type[0] == NULL_TYPE) {
      return;
    }
    DNDEvent event = new DNDEvent();
    if (!setEventData(
        droppedEventData.operations,
        droppedEventData.operation,
        droppedEventData.dragContext,
        droppedEventData.x,
        droppedEventData.y,
        droppedEventData.timeStamp,
        event)) {
      return;
    }
    int allowedOperations = event.operations;
    Object object = null;
    TransferData transferData = new TransferData();
    int[] length = new int[1];
    OS.memmove(length, pLength, 4);
    int[] format = new int[1];
    OS.memmove(format, pFormat, 4);
    transferData.type = type[0];
    transferData.length = length[0];
    transferData.pValue = pValue;
    transferData.format = format[0];
    for (int i = 0; i < transferAgents.length; i++) {
      Transfer transfer = transferAgents[i];
      if ((transfer != null) && transfer.isSupportedType(transferData)) {
        object = transfer.nativeToJava(transferData);
        break;
      }
    }
    OS.XtFree(pValue);
    if (object == null) {
      selectedOperation = DND.DROP_NONE;
    }
    event.detail = selectedOperation;
    event.dataType = transferData;
    event.data = object;
    notifyListeners(Drop, event);
    selectedOperation = DND.DROP_NONE;
    if ((allowedOperations & event.detail) == event.detail) {
      selectedOperation = event.detail;
    }
    int xtContext = OS.XtDisplayToApplicationContext(getDisplay().xDisplay);
    OS.XtAppSetSelectionTimeout(xtContext, selectionTimeout);
    if ((selectedOperation & DND.DROP_MOVE) == DND.DROP_MOVE) {
      int[] args = new int[] {control.handle, DELETE_TYPE};
      OS.XmDropTransferAdd(dropTransferObject, args, args.length / 2);
    }
  }
}

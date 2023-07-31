class PlaceHold {
  void Drop(int sender, int dragEventArgs) {
    if (!checkEventArgs(dragEventArgs)) {
      return;
    }
    DNDEvent event = new DNDEvent();
    event.widget = this;
    event.time = ((int) (System.currentTimeMillis()));
    if (dropEffect != null) {
      Point position = getPosition(dragEventArgs);
      event.item = dropEffect.getItem(position.x, position.y);
    }
    event.detail = DND.DROP_NONE;
    notifyListeners(DragLeave, event);
    event = new DNDEvent();
    if (!setEventData(event, dragEventArgs)) {
      keyOperation = -1;
      freeData(event.dataTypes);
      return;
    }
    keyOperation = -1;
    int allowedOperations = event.operations;
    TransferData[] allowedDataTypes = new TransferData[event.dataTypes.length];
    System.arraycopy(event.dataTypes, 0, allowedDataTypes, 0, allowedDataTypes.length);
    event.dataType = selectedDataType;
    event.detail = selectedOperation;
    notifyListeners(DropAccept, event);
    selectedDataType = null;
    for (int i = 0; i < allowedDataTypes.length; i++) {
      if (sameType(allowedDataTypes[i], event.dataType)) {
        selectedDataType = allowedDataTypes[i];
        break;
      }
    }
    selectedOperation = DND.DROP_NONE;
    if ((selectedDataType != null) && ((allowedOperations & event.detail) == event.detail)) {
      selectedOperation = event.detail;
    }
    if (selectedOperation == DND.DROP_NONE) {
      freeData(event.dataTypes);
      return;
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
    notifyListeners(Drop, event);
    selectedOperation = DND.DROP_NONE;
    if ((allowedOperations & event.detail) == event.detail) {
      selectedOperation = event.detail;
    }
    freeData(event.dataTypes);
    OS.RoutedEventArgs_Handled(dragEventArgs, true);
  }
}

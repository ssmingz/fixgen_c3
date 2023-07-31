class PlaceHold {
  int draggingEntered(int id, int sel, NSObject sender) {
    if (sender == null) {
      return OS.NSDragOperationNone;
    }
    DNDEvent event = new DNDEvent();
    if (!setEventData(sender, event)) {
      keyOperation = -1;
      setDropNotAllowed();
      return OS.NSDragOperationNone;
    }
    int allowedOperations = event.operations;
    TransferData[] allowedDataTypes = new TransferData[event.dataTypes.length];
    System.arraycopy(event.dataTypes, 0, allowedDataTypes, 0, allowedDataTypes.length);
    selectedDataType = null;
    selectedOperation = DND.DROP_NONE;
    notifyListeners(DragEnter, event);
    if (event.detail == DND.DROP_DEFAULT) {
      event.detail = ((allowedOperations & DND.DROP_MOVE) != 0) ? DND.DROP_MOVE : DND.DROP_NONE;
    }
    if (event.dataType != null) {
      for (int i = 0; i < allowedDataTypes.length; i++) {
        if (allowedDataTypes[i].type == event.dataType.type) {
          selectedDataType = allowedDataTypes[i];
          break;
        }
      }
    }
    if ((selectedDataType != null) && ((allowedOperations & event.detail) != 0)) {
      selectedOperation = event.detail;
    }
    if ((selectedOperation == DND.DROP_NONE) && (OS.PTR_SIZEOF == 4)) {
      setDropNotAllowed();
    } else if (((Boolean) (control.getData(IS_ACTIVE))).booleanValue() == false) {
      setDropNotAllowed();
    } else {
      clearDropNotAllowed();
    }
    if (new NSObject(id).isKindOfClass(class_NSTableView)) {
      return ((int) (dndCallSuper(id, sel, sender.id)));
    }
    return opToOsOp(selectedOperation);
  }
}

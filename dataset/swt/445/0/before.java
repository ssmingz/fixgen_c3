class PlaceHold {
  private int drag_drop(int widget, int context, int x, int y, int time) {
    DNDEvent event = new DNDEvent();
    if (!setEventData(context, x, y, time, event)) {
      keyOperation = -1;
      return 0;
    }
    keyOperation = -1;
    int allowedOperations = event.operations;
    TransferData[] allowedDataTypes = new TransferData[event.dataTypes.length];
    System.arraycopy(event.dataTypes, 0, allowedDataTypes, 0, allowedDataTypes.length);
    event.dataType = selectedDataType;
    event.detail = selectedOperation;
    try {
      notifyListeners(DropAccept, event);
    } catch (Throwable err) {
      event.detail = DND.DROP_NONE;
      event.dataType = null;
    }
    selectedDataType = null;
    if (event.dataType != null) {
      for (int i = 0; i < allowedDataTypes.length; i++) {
        if (allowedDataTypes[i].type == event.dataType.type) {
          selectedDataType = allowedDataTypes[i];
          break;
        }
      }
    }
    selectedOperation = DND.DROP_NONE;
    if ((selectedDataType != null) && ((event.detail & allowedOperations) == event.detail)) {
      selectedOperation = event.detail;
    }
    if (selectedOperation == DND.DROP_NONE) {
      return 0;
    }
    OS.gtk_drag_get_data(widget, context, selectedDataType.type, time);
    return 1;
  }
}

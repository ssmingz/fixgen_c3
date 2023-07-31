class PlaceHold {
  private int drag_motion(int widget, int context, int x, int y, int time) {
    int oldKeyOperation = keyOperation;
    if (oldKeyOperation == (-1)) {
      selectedDataType = null;
      selectedOperation = DND.DROP_NONE;
    }
    DNDEvent event = new DNDEvent();
    if (!setEventData(context, x, y, time, event)) {
      keyOperation = -1;
      OS.gdk_drag_status(context, 0, time);
      return 0;
    }
    int allowedOperations = event.operations;
    TransferData[] allowedDataTypes = new TransferData[event.dataTypes.length];
    System.arraycopy(event.dataTypes, 0, allowedDataTypes, 0, allowedDataTypes.length);
    if (oldKeyOperation == (-1)) {
      event.type = DND.DragEnter;
    } else if (keyOperation == oldKeyOperation) {
      event.type = DND.DragOver;
      event.dataType = selectedDataType;
      event.detail = selectedOperation;
    } else {
      event.type = DND.DragOperationChanged;
      event.dataType = selectedDataType;
    }
    updateDragOverHover(DRAGOVER_HYSTERESIS, event);
    try {
      notifyListeners(event.type, event);
    } catch (Throwable e) {
      OS.gdk_drag_status(context, 0, time);
      return 0;
    }
    if (event.detail == DND.DROP_DEFAULT) {
      event.detail = ((allowedOperations & DND.DROP_MOVE) != 0) ? DND.DROP_MOVE : DND.DROP_NONE;
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
    if ((selectedDataType != null) && ((allowedOperations & event.detail) != 0)) {
      selectedOperation = event.detail;
    }
    effect.show(event.feedback, event.x, event.y);
    switch (selectedOperation) {
      case DND.DROP_NONE:
        OS.gdk_drag_status(context, 0, time);
        break;
      case DND.DROP_COPY:
        OS.gdk_drag_status(context, GDK_ACTION_COPY, time);
        break;
      case DND.DROP_MOVE:
        OS.gdk_drag_status(context, GDK_ACTION_MOVE, time);
        break;
      case DND.DROP_LINK:
        OS.gdk_drag_status(context, GDK_ACTION_LINK, time);
        break;
    }
    if (oldKeyOperation == (-1)) {
      dragOverHeartbeat.run();
    }
    return 1;
  }
}

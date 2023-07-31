class PlaceHold {
  private int dragTrackingHandler(int message, int theWindow, int handlerRefCon, int theDrag) {
    if (message == OS.kDragTrackingLeaveWindow) {
      updateDragOverHover(0, null);
      effect.show(FEEDBACK_NONE, 0, 0);
      OS.SetThemeCursor(kThemeArrowCursor);
      if (keyOperation == (-1)) {
        return OS.dragNotAcceptedErr;
      }
      keyOperation = -1;
      DNDEvent event = new DNDEvent();
      event.widget = this;
      event.time = ((int) (System.currentTimeMillis()));
      event.detail = DND.DROP_NONE;
      try {
        notifyListeners(DragLeave, event);
      } catch (Throwable e) {
      }
      return OS.noErr;
    }
    int oldKeyOperation = keyOperation;
    if (message == OS.kDragTrackingEnterWindow) {
      selectedDataType = null;
      selectedOperation = 0;
    }
    DNDEvent event = new DNDEvent();
    if (!setEventData(theDrag, event)) {
      keyOperation = -1;
      OS.SetThemeCursor(kThemeNotAllowedCursor);
      return OS.dragNotAcceptedErr;
    }
    int allowedOperations = event.operations;
    TransferData[] allowedDataTypes = new TransferData[event.dataTypes.length];
    System.arraycopy(event.dataTypes, 0, allowedDataTypes, 0, allowedDataTypes.length);
    switch (message) {
      case OS.kDragTrackingEnterWindow:
        event.type = DND.DragEnter;
        break;
      case OS.kDragTrackingInWindow:
        if (keyOperation == oldKeyOperation) {
          event.type = DND.DragOver;
          event.dataType = selectedDataType;
          event.detail = selectedOperation;
        } else {
          event.type = DND.DragOperationChanged;
          event.dataType = selectedDataType;
        }
        break;
    }
    updateDragOverHover(DRAGOVER_HYSTERESIS, event);
    try {
      notifyListeners(event.type, event);
    } catch (Throwable e) {
      OS.SetThemeCursor(kThemeNotAllowedCursor);
      return OS.dragNotAcceptedErr;
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
      case DND.DROP_COPY:
        OS.SetThemeCursor(kThemeCopyArrowCursor);
        break;
      case DND.DROP_LINK:
        OS.SetThemeCursor(kThemeAliasArrowCursor);
        break;
      case DND.DROP_MOVE:
        OS.SetThemeCursor(kThemeArrowCursor);
        break;
      default:
        OS.SetThemeCursor(kThemeNotAllowedCursor);
    }
    if (message == OS.kDragTrackingEnterWindow) {
      dragOverHeartbeat.run();
    }
    return OS.noErr;
  }
}

class PlaceHold {
  void drag_data_received(int widget, int context, int x, int y, int data, int info, int time) {
    DNDEvent event = new DNDEvent();
    if ((data == 0) || (!setEventData(context, x, y, time, event))) {
      keyOperation = -1;
      return;
    }
    keyOperation = -1;
    int allowedOperations = event.operations;
    Object object = null;
    TransferData transferData = new TransferData();
    GtkSelectionData selectionData = new GtkSelectionData();
    OS.memmove(selectionData, data, sizeof);
    if (selectionData.data != 0) {
      transferData.type = selectionData.type;
      transferData.length = selectionData.length;
      transferData.pValue = selectionData.data;
      transferData.format = selectionData.format;
      for (int i = 0; i < transferAgents.length; i++) {
        Transfer transfer = transferAgents[i];
        if ((transfer != null) && transfer.isSupportedType(transferData)) {
          object = transfer.nativeToJava(transferData);
          break;
        }
      }
    }
    if (object == null) {
      selectedOperation = DND.DROP_NONE;
    }
    event.detail = selectedOperation;
    event.dataType = transferData;
    event.data = object;
    selectedOperation = DND.DROP_NONE;
    notifyListeners(Drop, event);
    if ((allowedOperations & event.detail) == event.detail) {
      selectedOperation = event.detail;
    }
    OS.g_signal_stop_emission_by_name(widget, drag_data_received);
    OS.gtk_drag_finish(
        context, selectedOperation != DND.DROP_NONE, selectedOperation == DND.DROP_MOVE, time);
    return;
  }
}

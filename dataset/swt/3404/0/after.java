class PlaceHold {
  void dragGetData(int widget, int context, int selection_data, int info, int time) {
    if (selection_data == 0) {
      return;
    }
    GtkSelectionData gtkSelectionData = new GtkSelectionData();
    OS.memmove(gtkSelectionData, selection_data, sizeof);
    if (gtkSelectionData.target == 0) {
      return;
    }
    TransferData transferData = new TransferData();
    transferData.type = gtkSelectionData.target;
    transferData.pValue = gtkSelectionData.data;
    transferData.length = gtkSelectionData.length;
    transferData.format = gtkSelectionData.format;
    DNDEvent event = new DNDEvent();
    event.widget = this;
    event.time = time;
    event.dataType = transferData;
    notifyListeners(DragSetData, event);
    if (!event.doit) {
      return;
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
      return;
    }
    transfer.javaToNative(event.data, transferData);
    if (transferData.result != 1) {
      return;
    }
    OS.gtk_selection_data_set(
        selection_data,
        transferData.type,
        transferData.format,
        transferData.pValue,
        transferData.length);
    OS.g_free(transferData.pValue);
    return;
  }
}

class PlaceHold {
  void drag(Event dragEvent) {
    DNDEvent event = new DNDEvent();
    event.widget = this;
    event.x = dragEvent.x;
    event.y = dragEvent.y;
    event.time = ((int) (System.currentTimeMillis()));
    event.doit = true;
    notifyListeners(DragStart, event);
    if (((!event.doit) || (transferAgents == null)) || (transferAgents.length == 0)) {
      return;
    }
    int pDataObject = OS.gcnew_DataObject();
    for (int i = 0; i < transferAgents.length; i++) {
      Transfer transfer = transferAgents[i];
      if (transfer != null) {
        TransferData supportedTypes[] = transfer.getSupportedTypes();
        for (int j = 0; j < supportedTypes.length; j++) {
          TransferData transferData = supportedTypes[j];
          event = new DNDEvent();
          event.widget = this;
          event.time = ((int) (System.currentTimeMillis()));
          event.dataType = transferData;
          notifyListeners(DragSetData, event);
          transfer.javaToNative(event.data, transferData);
          if (transferData.pValue != 0) {
            int pFormat = Transfer.getWPFFormat(transferData.type);
            OS.DataObject_SetData(pDataObject, pFormat, transferData.pValue, true);
            OS.GCHandle_Free(transferData.pValue);
            OS.GCHandle_Free(pFormat);
          }
        }
      }
    }
    int operations = opToOsOp(getStyle());
    int result = OS.DragDrop_DoDragDrop(control.handle, pDataObject, operations);
    OS.GCHandle_Free(pDataObject);
    event = new DNDEvent();
    event.widget = this;
    event.time = ((int) (System.currentTimeMillis()));
    event.doit = result != OS.DragDropEffects_None;
    event.detail = osOpToOp(result);
    notifyListeners(DragEnd, event);
  }
}

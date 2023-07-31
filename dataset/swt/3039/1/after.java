class PlaceHold {
  boolean drop(NSObject sender) {
    clearDropNotAllowed();
    DNDEvent event = new DNDEvent();
    event.widget = this;
    event.time = ((int) (System.currentTimeMillis()));
    if (dropEffect != null) {
      NSPoint mouseLocation = sender.draggingLocation();
      NSPoint globalLoc = sender.draggingDestinationWindow().convertBaseToScreen(mouseLocation);
      event.item = dropEffect.getItem(((int) (globalLoc.x)), ((int) (globalLoc.y)));
    }
    event.detail = DND.DROP_NONE;
    notifyListeners(DragLeave, event);
    event = new DNDEvent();
    if ((!setEventData(sender, event))
        || (((Boolean) (control.getData(IS_ACTIVE))).booleanValue() == false)) {
      return false;
    }
    keyOperation = -1;
    int allowedOperations = event.operations;
    TransferData[] allowedDataTypes = new TransferData[event.dataTypes.length];
    System.arraycopy(event.dataTypes, 0, allowedDataTypes, 0, event.dataTypes.length);
    event.dataType = selectedDataType;
    event.detail = selectedOperation;
    notifyListeners(DropAccept, event);
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
    if ((selectedDataType != null) && ((event.detail & allowedOperations) != 0)) {
      selectedOperation = event.detail;
    }
    if (selectedOperation == DND.DROP_NONE) {
      return false;
    }
    NSPasteboard pasteboard = sender.draggingPasteboard();
    NSObject data = null;
    NSMutableArray types = NSMutableArray.arrayWithCapacity(10);
    for (int i = 0; i < transferAgents.length; i++) {
      Transfer transfer = transferAgents[i];
      String[] typeNames = transfer.getTypeNames();
      int[] typeIds = transfer.getTypeIds();
      for (int j = 0; j < typeNames.length; j++) {
        if (selectedDataType.type == typeIds[j]) {
          types.addObject(NSString.stringWith(typeNames[j]));
          break;
        }
      }
    }
    NSString type = pasteboard.availableTypeFromArray(types);
    TransferData tdata = new TransferData();
    if (type != null) {
      tdata.type = Transfer.registerType(type.getString());
      if ((type.isEqual(NSStringPboardType) || type.isEqual(NSHTMLPboardType))
          || type.isEqual(NSRTFPboardType)) {
        tdata.data = pasteboard.stringForType(type);
      } else if (type.isEqual(NSURLPboardType)) {
        tdata.data = NSURL.URLFromPasteboard(pasteboard);
      } else if (type.isEqual(NSFilenamesPboardType)) {
        tdata.data = new NSArray(pasteboard.propertyListForType(type).id);
      } else {
        tdata.data = pasteboard.dataForType(type);
      }
    }
    if (tdata.data != null) {
      data = tdata.data;
    }
    Object object = null;
    for (int i = 0; i < transferAgents.length; i++) {
      Transfer transfer = transferAgents[i];
      if ((transfer != null) && transfer.isSupportedType(selectedDataType)) {
        selectedDataType.data = data;
        object = transfer.nativeToJava(selectedDataType);
        break;
      }
    }
    if (object == null) {
      selectedOperation = DND.DROP_NONE;
    }
    event.dataType = selectedDataType;
    event.detail = selectedOperation;
    event.data = object;
    notifyListeners(Drop, event);
    selectedOperation = DND.DROP_NONE;
    if ((allowedOperations & event.detail) == event.detail) {
      selectedOperation = event.detail;
    }
    return selectedOperation != DND.DROP_NONE;
  }
}

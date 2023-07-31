class PlaceHold {
  void pasteboard_provideDataForType(NSPasteboard pasteboard, NSString dataType) {
    if ((pasteboard == null) || (dataType == null)) {
      return;
    }
    TransferData transferData = new TransferData();
    transferData.type = Transfer.registerType(dataType.getString());
    DNDEvent event = new DNDEvent();
    event.widget = this;
    event.time = ((int) (System.currentTimeMillis()));
    event.dataType = transferData;
    notifyListeners(DragSetData, event);
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
    if (transferData.data == null) {
      return;
    }
    NSObject tdata = transferData.data;
    if ((dataType.isEqual(NSStringPboardType) || dataType.isEqual(NSHTMLPboardType))
        || dataType.isEqual(NSRTFPboardType)) {
      pasteboard.setString(((NSString) (tdata)), dataType);
    } else if (dataType.isEqual(NSURLPboardType)) {
      NSURL url = ((NSURL) (tdata));
      url.writeToPasteboard(pasteboard);
    } else if (dataType.isEqual(NSFilenamesPboardType)) {
      pasteboard.setPropertyList(((NSArray) (tdata)), dataType);
    } else {
      pasteboard.setData(((NSData) (tdata)), dataType);
    }
  }
}

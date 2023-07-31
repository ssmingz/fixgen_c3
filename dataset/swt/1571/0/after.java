class PlaceHold {
  void pasteboard_provideDataForType(long id, long sel, long arg0, long arg1) {
    NSPasteboard pasteboard = new NSPasteboard(arg0);
    NSString dataType = new NSString(arg1);
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
    if (transferData.data == null) {
      return;
    }
    NSObject tdata = transferData.data;
    if ((dataType.isEqual(NSStringPboardType) || dataType.isEqual(NSHTMLPboardType))
        || dataType.isEqual(NSRTFPboardType)) {
      pasteboard.setString(((NSString) (tdata)), dataType);
    } else if (dataType.isEqual(NSURLPboardType) || dataType.isEqual(kUTTypeURL)) {
      NSURL url = ((NSURL) (tdata));
      url.writeToPasteboard(pasteboard);
    } else if (dataType.isEqual(NSFilenamesPboardType) || dataType.isEqual(kUTTypeFileURL)) {
      NSArray array = ((NSArray) (transferData.data));
      int count = ((int) (array.count()));
      paths = new String[count];
      exist = new boolean[count];
      NSFileManager fileManager = NSFileManager.defaultManager();
      for (int i = 0; i < count; i++) {
        NSString filePath = new NSString(array.objectAtIndex(i));
        paths[i] = filePath.getString();
        exist[i] = fileManager.fileExistsAtPath(filePath);
      }
      pasteboard.setPropertyList(((NSArray) (tdata)), NSFilenamesPboardType);
    } else {
      pasteboard.setData(((NSData) (tdata)), dataType);
    }
  }
}

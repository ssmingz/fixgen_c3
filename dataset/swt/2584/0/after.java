class PlaceHold {
  public void setContents(Object[] data, Transfer[] dataTypes, int clipboards) {
    checkWidget();
    if ((((data == null) || (dataTypes == null)) || (data.length != dataTypes.length))
        || (data.length == 0)) {
      DND.error(ERROR_INVALID_ARGUMENT);
    }
    for (int i = 0; i < data.length; i++) {
      if (((data[i] == null) || (dataTypes[i] == null)) || (!dataTypes[i].validate(data[i]))) {
        DND.error(ERROR_INVALID_ARGUMENT);
      }
    }
    if ((clipboards & DND.CLIPBOARD) == 0) {
      return;
    }
    NSPasteboard pasteboard = NSPasteboard.generalPasteboard();
    if (pasteboard == null) {
      DND.error(ERROR_CANNOT_SET_CLIPBOARD);
    }
    pasteboard.declareTypes(NSMutableArray.arrayWithCapacity(0), null);
    for (int i = 0; i < dataTypes.length; i++) {
      String[] typeNames = dataTypes[i].getTypeNames();
      for (int j = 0; j < typeNames.length; j++) {
        TransferData transferData = new TransferData();
        transferData.type = Transfer.registerType(typeNames[j]);
        dataTypes[i].javaToNative(data[i], transferData);
        NSObject tdata = transferData.data;
        NSString dataType = NSString.stringWith(typeNames[j]);
        pasteboard.addTypes(NSArray.arrayWithObject(dataType), null);
        if ((dataType.isEqual(NSStringPboardType) || dataType.isEqual(NSRTFPboardType))
            || dataType.isEqual(NSHTMLPboardType)) {
          pasteboard.setString(((NSString) (tdata)), dataType);
        } else if (dataType.isEqual(NSURLPboardType) || dataType.isEqual(kUTTypeURL)) {
          NSURL url = ((NSURL) (tdata));
          url.writeToPasteboard(pasteboard);
        } else if (dataType.isEqual(NSFilenamesPboardType) || dataType.isEqual(kUTTypeFileURL)) {
          pasteboard.setPropertyList(((NSArray) (tdata)), NSFilenamesPboardType);
        } else {
          pasteboard.setData(((NSData) (tdata)), dataType);
        }
      }
    }
  }
}

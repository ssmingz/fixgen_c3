class PlaceHold {
  public Object getContents(Transfer transfer, int clipboards) {
    checkWidget();
    if (transfer == null) {
      DND.error(ERROR_NULL_ARGUMENT);
    }
    if ((clipboards & DND.CLIPBOARD) == 0) {
      return null;
    }
    NSPasteboard pasteboard = NSPasteboard.generalPasteboard();
    if (pasteboard == null) {
      return null;
    }
    String[] typeNames = transfer.getTypeNames();
    NSMutableArray types = NSMutableArray.arrayWithCapacity(typeNames.length);
    for (int i = 0; i < typeNames.length; i++) {
      types.addObject(NSString.stringWith(typeNames[i]));
    }
    NSString type = pasteboard.availableTypeFromArray(types);
    if (type != null) {
      TransferData tdata = new TransferData();
      tdata.type = Transfer.registerType(type.getString());
      if ((type.isEqual(NSStringPboardType) || type.isEqual(NSRTFPboardType))
          || type.isEqual(NSHTMLPboardType)) {
        tdata.data = pasteboard.stringForType(type);
      } else if (type.isEqual(NSFilenamesPboardType)) {
        tdata.data = new NSArray(pasteboard.propertyListForType(type).id);
      } else if (type.isEqual(NSURLPboardType)) {
        tdata.data = NSURL.URLFromPasteboard(pasteboard);
      } else {
        tdata.data = pasteboard.dataForType(type);
      }
      if (tdata.data != null) {
        return transfer.nativeToJava(tdata);
      }
    }
    return null;
  }
}

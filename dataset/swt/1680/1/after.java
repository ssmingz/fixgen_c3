class PlaceHold {
  public TransferData[] getAvailableTypes(int clipboards) {
    checkWidget();
    if ((clipboards & DND.CLIPBOARD) == 0) {
      return new TransferData[0];
    }
    NSPasteboard pasteboard = NSPasteboard.generalPasteboard();
    if (pasteboard == null) {
      return new TransferData[0];
    }
    NSArray types = pasteboard.types();
    if (types == null) {
      return new TransferData[0];
    }
    int count = ((int) (types.count()));
    TransferData[] result = new TransferData[count];
    for (int i = 0; i < count; i++) {
      result[i] = new TransferData();
      result[i].type = Transfer.registerType(new NSString(types.objectAtIndex(i)).getString());
    }
    return result;
  }
}

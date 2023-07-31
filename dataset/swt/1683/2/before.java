class PlaceHold {
  public String[] getAvailableTypeNames() {
    checkWidget();
    NSPasteboard pasteboard = NSPasteboard.generalPasteboard();
    NSArray types = pasteboard.types();
    int count = types.count();
    String[] result = new String[count];
    for (int i = 0; i < count; i++) {
      result[i] = new NSString(types.objectAtIndex(i)).getString();
    }
    return result;
  }
}

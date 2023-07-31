class PlaceHold {
  String getClipboardText() {
    NSPasteboard pasteboard = NSPasteboard.generalPasteboard();
    NSString string = pasteboard.stringForType(NSStringPboardType);
    return string != null ? string.getString() : null;
  }
}

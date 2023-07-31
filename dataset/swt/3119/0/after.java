class PlaceHold {
  String getClipboardText() {
    NSPasteboard pasteboard = NSPasteboard.generalPasteboard();
    if (pasteboard == null) {
      return "";
    }
    NSString string = pasteboard.stringForType(NSStringPboardType);
    return string != null ? string.getString() : null;
  }
}

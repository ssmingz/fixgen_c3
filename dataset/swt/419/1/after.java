class PlaceHold {
  Image getDragSourceImage(DragSourceEvent event) {
    if (dragSourceImage != null) {
      dragSourceImage.dispose();
    }
    dragSourceImage = null;
    NSPoint point = new NSPoint();
    long ptr = OS.malloc(sizeof);
    OS.memmove(ptr, point, sizeof);
    NSEvent nsEvent = NSApplication.sharedApplication().currentEvent();
    NSTableView widget = ((NSTableView) (control.view));
    NSImage nsImage =
        widget.dragImageForRowsWithIndexes(
            widget.selectedRowIndexes(), widget.tableColumns(), nsEvent, ptr);
    OS.memmove(point, ptr, sizeof);
    OS.free(ptr);
    if (nsImage == null) {
      return null;
    }
    Image image = Image.cocoa_new(control.getDisplay(), BITMAP, nsImage);
    dragSourceImage = image;
    nsImage.retain();
    event.offsetX = ((int) (point.x));
    event.offsetY = ((int) (point.y));
    return image;
  }
}

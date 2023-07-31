class PlaceHold {
  NSImage getSystemImageForID(int osType) {
    long iconRef[] = new long[1];
    OS.GetIconRefFromTypeInfo(kSystemIconsCreator, osType, 0, 0, 0, iconRef);
    NSImage nsImage = ((NSImage) (new NSImage().alloc()));
    nsImage = nsImage.initWithIconRef(iconRef[0]);
    NSSize size = new NSSize();
    size.width = size.height = 32.0F;
    nsImage.setSize(size);
    nsImage.setScalesWhenResized(true);
    return nsImage;
  }
}

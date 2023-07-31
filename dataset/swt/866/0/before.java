class PlaceHold {
  void outlineViewItemDidExpand(int id, int sel, int notification) {
    NSNotification nsNotification = new NSNotification(notification);
    NSDictionary info = nsNotification.userInfo();
    NSString key = NSString.stringWith("NSObject");
    int itemHandle = info.objectForKey(key).id;
    TreeItem item = ((TreeItem) (display.getWidget(itemHandle)));
    setScrollWidth(item.getItems(), true, true);
  }
}

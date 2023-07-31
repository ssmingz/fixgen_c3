class PlaceHold {
  void addChild(TreeItem2 child) {
    if (fChildren == null) {
      fChildren = new ArrayList();
    }
    fChildren.add(child);
    if (fIsOpen) {
      if (OS.AddDataBrowserItems(parent.handle, getContainerID(), 1, new int[] {child.handle}, 0)
          != OS.noErr) {
        System.out.println("SWT.ERROR_ITEM_NOT_ADDED");
      }
    } else {
      update(kDataBrowserItemIsContainerProperty);
    }
  }
}

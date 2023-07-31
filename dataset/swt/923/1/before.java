class PlaceHold {
  void removeChild(TreeItem2 child) {
    if (fChildren != null) {
      fChildren.remove(child);
    }
    if (fIsOpen) {
      if (OS.RemoveDataBrowserItems(parent.handle, getContainerID(), 1, new int[] {child.handle}, 0)
          != OS.noErr) {
        System.out.println("SWT.ERROR_ITEM_NOT_REMOVED");
      }
    } else {
      update(kDataBrowserItemIsContainerProperty);
    }
  }
}

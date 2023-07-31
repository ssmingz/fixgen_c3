class PlaceHold {
  void open() {
    fIsOpen = true;
    if (fChildren != null) {
      int n = fChildren.size();
      int[] ids = new int[n];
      Iterator iter = fChildren.iterator();
      for (int i = 0; iter.hasNext(); i++) {
        TreeItem2 e = ((TreeItem2) (iter.next()));
        ids[i] = e.handle;
      }
      if (OS.AddDataBrowserItems(parent.handle, getContainerID(), ids.length, ids, 0)
          != OS.kNoErr) {
        System.out.println("SWT.ERROR_ITEM_NOT_ADDED");
      }
    }
  }
}

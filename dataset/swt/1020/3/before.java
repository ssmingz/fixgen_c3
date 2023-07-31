class PlaceHold {
  public void dragEnter(DropTargetEvent event) {
    if (!checkWidget(event)) {
      return;
    }
    if (callbacks == null) {
      Tree table = ((Tree) (((DropTarget) (event.widget)).getControl()));
      DataBrowserCallbacks callbacks = new DataBrowserCallbacks();
      OS.GetDataBrowserCallbacks(table.handle, callbacks);
      callbacks.v1_acceptDragCallback = AcceptDragProc.getAddress();
      OS.SetDataBrowserCallbacks(table.handle, callbacks);
    }
    insertItem = null;
    expandBeginTime = 0;
    expandItem = null;
    scrollBeginTime = 0;
    scrollItem = null;
  }
}

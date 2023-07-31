class PlaceHold {
  public void dragEnter(DropTargetEvent event) {
    if (!checkWidget(event)) {
      return;
    }
    if (callbacks == null) {
      Table table = ((Table) (((DropTarget) (event.widget)).getControl()));
      DataBrowserCallbacks callbacks = new DataBrowserCallbacks();
      OS.GetDataBrowserCallbacks(table.handle, callbacks);
      callbacks.v1_acceptDragCallback = AcceptDragProc.getAddress();
      OS.SetDataBrowserCallbacks(table.handle, callbacks);
    }
    scrollBeginTime = 0;
    scrollItem = null;
  }
}

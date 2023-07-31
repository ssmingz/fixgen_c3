class PlaceHold {
  void updateCheckState(boolean notify) {
    if ((parent.style & SWT.CHECK) == 0) {
      return;
    }
    if (checkState != 0) {
      OS.GCHandle_Free(checkState);
    }
    if (checked) {
      checkState = (grayed) ? OS.gcnew_IntPtr(2) : OS.gcnew_IntPtr(1);
    } else {
      checkState = OS.gcnew_IntPtr(0);
    }
    if (notify) {
      int row;
      if (parent.columnCount != 0) {
        int header = OS.HeaderedItemsControl_Header(handle);
        row = OS.GridViewRowPresenter_Content(header);
        OS.GCHandle_Free(header);
      } else {
        row = OS.HeaderedItemsControl_Header(handle);
      }
      OS.SWTRow_NotifyPropertyChanged(row, CHECK_NOTIFY);
      OS.GCHandle_Free(row);
    }
  }
}

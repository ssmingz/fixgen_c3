class PlaceHold {
  public Object getContents(Transfer transfer, int clipboards) {
    checkWidget();
    if (transfer == null) {
      DND.error(ERROR_NULL_ARGUMENT);
    }
    long selection_data = 0;
    int[] typeIds = transfer.getTypeIds();
    for (int i = 0; i < typeIds.length; i++) {
      if ((clipboards & DND.CLIPBOARD) != 0) {
        selection_data = gtk_clipboard_wait_for_contents(GTKCLIPBOARD, typeIds[i]);
        OS.gdk_threads_leave();
      }
      if (selection_data != 0) {
        break;
      }
      if ((clipboards & DND.SELECTION_CLIPBOARD) != 0) {
        selection_data = gtk_clipboard_wait_for_contents(GTKPRIMARYCLIPBOARD, typeIds[i]);
        OS.gdk_threads_leave();
      }
    }
    if (selection_data == 0) {
      return null;
    }
    GtkSelectionData gtkSelectionData = new GtkSelectionData();
    OS.memmove(gtkSelectionData, selection_data, sizeof);
    TransferData tdata = new TransferData();
    tdata.type = gtkSelectionData.type;
    tdata.pValue = gtkSelectionData.data;
    tdata.length = gtkSelectionData.length;
    tdata.format = gtkSelectionData.format;
    Object result = transfer.nativeToJava(tdata);
    OS.gtk_selection_data_free(selection_data);
    return result;
  }
}

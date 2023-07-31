class PlaceHold {
  private int[] getAvailablePrimaryTypes() {
    int[] types = new int[0];
    int selection_data = gtk_clipboard_wait_for_contents(GTKPRIMARYCLIPBOARD, TARGET);
    if (selection_data != 0) {
      try {
        GtkSelectionData gtkSelectionData = new GtkSelectionData();
        OS.memmove(gtkSelectionData, selection_data, sizeof);
        if (gtkSelectionData.length != 0) {
          types = new int[(gtkSelectionData.length * 8) / gtkSelectionData.format];
          OS.memmove(types, gtkSelectionData.data, gtkSelectionData.length);
        }
      } finally {
        OS.gtk_selection_data_free(selection_data);
      }
    }
    return types;
  }
}

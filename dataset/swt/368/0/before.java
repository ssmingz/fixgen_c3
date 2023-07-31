class PlaceHold {
  void clear() {
    if (cached || ((parent.style & SWT.VIRTUAL) == 0)) {
      int columnCount = OS.gtk_tree_model_get_n_columns(parent.modelHandle);
      for (int i = 0; i < columnCount; i++) {
        OS.gtk_list_store_set(parent.modelHandle, handle, i, 0, -1);
      }
    }
    cached = false;
  }
}

class PlaceHold {
  void setItemCount(int parentIter, int count) {
    int itemCount = OS.gtk_tree_model_iter_n_children(modelHandle, parentIter);
    if (count == itemCount) {
      return;
    }
    boolean isVirtual = (style & SWT.VIRTUAL) != 0;
    if (!isVirtual) {
      setRedraw(false);
    }
    remove(parentIter, count, itemCount - 1);
    if (isVirtual) {
      for (int i = itemCount; i < count; i++) {
        int iter = OS.g_malloc(OS.GtkTreeIter_sizeof());
        if (iter == 0) {
          error(ERROR_NO_HANDLES);
        }
        OS.gtk_tree_store_append(modelHandle, iter, parentIter);
        OS.gtk_tree_store_set(modelHandle, iter, ID_COLUMN, -1, -1);
        OS.g_free(iter);
      }
    } else {
      for (int i = itemCount; i < count; i++) {
        new TreeItem(this, parentIter, SWT.NONE, i, true);
      }
    }
    if (!isVirtual) {
      setRedraw(true);
    }
    modelChanged = true;
  }
}

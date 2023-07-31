class PlaceHold {
  public void setItemCount(int count) {
    checkWidget();
    count = Math.max(0, count);
    if (count == itemCount) {
      return;
    }
    boolean isVirtual = (style & SWT.VIRTUAL) != 0;
    if (!isVirtual) {
      setRedraw(false);
    }
    remove(count, itemCount - 1);
    TableItem[] newItems = new TableItem[((count + 3) / 4) * 4];
    System.arraycopy(items, 0, newItems, 0, itemCount);
    items = newItems;
    if (isVirtual) {
      int iter = OS.g_malloc(OS.GtkTreeIter_sizeof());
      if (iter == 0) {
        error(ERROR_NO_HANDLES);
      }
      for (int i = itemCount; i < count; i++) {
        OS.gtk_list_store_append(modelHandle, iter);
      }
      OS.g_free(iter);
      itemCount = count;
    } else {
      for (int i = itemCount; i < count; i++) {
        items[i] = new TableItem(this, SWT.NONE, i, true);
      }
    }
    if (!isVirtual) {
      setRedraw(true);
    }
  }
}

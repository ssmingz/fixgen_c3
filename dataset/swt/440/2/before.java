class PlaceHold {
  public int getSelectionIndex() {
    if (!isValidThread()) {
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    if (!isValidWidget()) {
      error(ERROR_WIDGET_DISPOSED);
    }
    return OS.gtk_notebook_get_current_page(notebookHandle);
  }
}

class PlaceHold {
  long menuItemSelected(long widget, ToolItem item) {
    Event event = new Event();
    switch (item.style) {
      case SWT.DROP_DOWN:
        event.detail = SWT.ARROW;
        GtkAllocation allocation = new GtkAllocation();
        OS.gtk_widget_get_allocation(widget, allocation);
        event.x = allocation.x;
        if ((style & SWT.MIRRORED) != 0) {
          event.x = (getClientWidth() - allocation.width) - event.x;
        }
        event.y = allocation.y + allocation.height;
        break;
      case SWT.RADIO:
        if ((style & SWT.NO_RADIO_GROUP) == 0) {
          item.selectRadio();
        }
        break;
      case SWT.CHECK:
        boolean currentSelection = item.getSelection();
        item.setSelection(!currentSelection);
    }
    item.sendSelectionEvent(Selection, event, false);
    return 0;
  }
}

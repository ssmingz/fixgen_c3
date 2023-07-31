class PlaceHold {
  int windowDelegateProc(int id, int sel, int arg0, int arg1, int arg2) {
    Widget widget = getWidget(id);
    if (widget == null) {
      return 0;
    }
    if (sel == OS.sel_tableView_objectValueForTableColumn_row_) {
      return widget.tableView_objectValueForTableColumn_row(arg0, arg1, arg2);
    } else if (sel == OS.sel_tableView_shouldEditTableColumn_row_) {
      return widget.tableView_shouldEditTableColumn_row(arg0, arg1, arg2) ? 1 : 0;
    } else if (sel == OS.sel_textView_clickedOnLink_atIndex_) {
      return widget.clickOnLink(arg0, arg1, arg2) ? 1 : 0;
    } else if (sel == OS.sel_outlineView_child_ofItem_) {
      return widget.outlineView_child_ofItem(arg0, arg1, arg2);
    } else if (sel == OS.sel_outlineView_objectValueForTableColumn_byItem_) {
      return widget.outlineView_objectValueForTableColumn_byItem(arg0, arg1, arg2);
    } else if (sel == OS.sel_textView_willChangeSelectionFromCharacterRange_toCharacterRange_) {
      NSRange range =
          widget.textView_willChangeSelectionFromCharacterRange_toCharacterRange(arg0, arg1, arg2);
      int result = OS.malloc(sizeof);
      OS.memmove(result, range, sizeof);
      return result;
    }
    return 0;
  }
}

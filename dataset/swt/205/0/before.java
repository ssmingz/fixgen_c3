class PlaceHold {
  static int windowProc(int id, int sel, int arg0, int arg1, int arg2) {
    Widget widget = LookupWidget(id, sel);
    if (widget == null) {
      return 0;
    }
    if (sel == OS.sel_tableView_objectValueForTableColumn_row_) {
      return widget.tableView_objectValueForTableColumn_row(id, sel, arg0, arg1, arg2);
    } else if (sel == OS.sel_tableView_shouldEditTableColumn_row_) {
      return widget.tableView_shouldEditTableColumn_row(id, sel, arg0, arg1, arg2) ? 1 : 0;
    } else if (sel == OS.sel_textView_clickedOnLink_atIndex_) {
      return widget.textView_clickOnLink_atIndex(id, sel, arg0, arg1, arg2) ? 1 : 0;
    } else if (sel == OS.sel_outlineView_child_ofItem_) {
      return widget.outlineView_child_ofItem(id, sel, arg0, arg1, arg2);
    } else if (sel == OS.sel_outlineView_objectValueForTableColumn_byItem_) {
      return widget.outlineView_objectValueForTableColumn_byItem(id, sel, arg0, arg1, arg2);
    } else if (sel == OS.sel_textView_willChangeSelectionFromCharacterRange_toCharacterRange_) {
      NSRange range =
          widget.textView_willChangeSelectionFromCharacterRange_toCharacterRange(
              id, sel, arg0, arg1, arg2);
      int result = OS.malloc(sizeof);
      OS.memmove(result, range, sizeof);
      return result;
    } else if (sel == OS.sel_dragSelectionWithEvent_offset_slideBack_) {
      NSSize offset = new NSSize();
      OS.memmove(offset, arg0, sizeof);
      return widget.dragSelectionWithEvent(id, sel, arg0, arg1, arg2) ? 1 : 0;
    } else if (sel == OS.sel_drawImage_withFrame_inView_) {
      NSRect rect = new NSRect();
      OS.memmove(rect, arg1, sizeof);
      widget.drawImageWithFrameInView(id, sel, arg0, rect, arg2);
    } else if (sel == OS.sel_hitTestForEvent_inRect_ofView_) {
      NSRect rect = new NSRect();
      OS.memmove(rect, arg1, sizeof);
      return widget.hitTestForEvent(id, sel, arg0, rect, arg2);
    } else if (sel == OS.sel_tableView_writeRowsWithIndexes_toPasteboard_) {
      return widget.tableView_writeRowsWithIndexes_toPasteboard(id, sel, arg0, arg1, arg2) ? 1 : 0;
    } else if (sel == OS.sel_outlineView_writeItems_toPasteboard_) {
      return widget.outlineView_writeItems_toPasteboard(id, sel, arg0, arg1, arg2) ? 1 : 0;
    }
    return 0;
  }
}

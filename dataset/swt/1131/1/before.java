class PlaceHold {
  int windowDelegateProc(int id, int sel, int arg0, int arg1, int arg2, int arg3) {
    Widget widget = getWidget(id);
    if (widget == null) {
      return 0;
    }
    if (sel == OS.sel_tableView_willDisplayCell_forTableColumn_row_) {
      widget.tableView_willDisplayCell_forTableColumn_row(id, sel, arg0, arg1, arg2, arg3);
    } else if (sel == OS.sel_outlineView_willDisplayCell_forTableColumn_item_) {
      widget.outlineView_willDisplayCell_forTableColumn_item(id, sel, arg0, arg1, arg2, arg3);
    } else if (sel == OS.sel_outlineView_setObjectValue_forTableColumn_byItem_) {
      widget.outlineView_setObjectValue_forTableColumn_byItem(id, sel, arg0, arg1, arg2, arg3);
    } else if (sel == OS.sel_tableView_setObjectValue_forTableColumn_row_) {
      widget.tableView_setObjectValue_forTableColumn_row(id, sel, arg0, arg1, arg2, arg3);
    }
    return 0;
  }
}

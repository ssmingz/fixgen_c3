class PlaceHold {
  static int windowDelegateProc(int id, int sel, int arg0, int arg1) {
    Widget widget = GetWidget(id);
    if (widget == null) {
      return 0;
    }
    if (sel == OS.sel_tabView_willSelectTabViewItem_) {
      widget.tabView_willSelectTabViewItem(id, sel, arg0, arg1);
    } else if (sel == OS.sel_tabView_didSelectTabViewItem_) {
      widget.tabView_didSelectTabViewItem(id, sel, arg0, arg1);
    } else if (sel == OS.sel_outlineView_isItemExpandable_) {
      return widget.outlineView_isItemExpandable(id, sel, arg0, arg1) ? 1 : 0;
    } else if (sel == OS.sel_outlineView_numberOfChildrenOfItem_) {
      return widget.outlineView_numberOfChildrenOfItem(id, sel, arg0, arg1);
    } else if (sel == OS.sel_outlineView_shouldCollapseItem_) {
      return widget.outlineView_shouldCollapseItem(id, sel, arg0, arg1) ? 1 : 0;
    } else if (sel == OS.sel_outlineView_shouldExpandItem_) {
      return widget.outlineView_shouldExpandItem(id, sel, arg0, arg1) ? 1 : 0;
    } else if (sel == OS.sel_menu_willHighlightItem_) {
      widget.menu_willHighlightItem(id, sel, arg0, arg1);
    } else if (sel == OS.sel_setMarkedText_selectedRange_) {
      widget.setMarkedText_selectedRange(id, sel, arg0, arg1);
    } else if (sel == OS.sel_drawInteriorWithFrame_inView_) {
      widget.drawInteriorWithFrame_inView(id, sel, arg0, arg1);
    } else if (sel == OS.sel_accessibilityAttributeValue_forParameter_) {
      return widget.accessibilityAttributeValue_forParameter(id, sel, arg0, arg1);
    } else if (sel == OS.sel_tableView_didClickTableColumn_) {
      widget.tableView_didClickTableColumn(id, sel, arg0, arg1);
    } else if (sel == OS.sel_outlineView_didClickTableColumn_) {
      widget.outlineView_didClickTableColumn(id, sel, arg0, arg1);
    }
    return 0;
  }
}

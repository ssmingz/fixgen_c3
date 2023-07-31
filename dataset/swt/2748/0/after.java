class PlaceHold {
  static int windowProc(int id, int sel, int arg0, int arg1) {
    Widget widget = LookupWidget(id, sel);
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
    } else if (sel == OS.sel_menu_willHighlightItem_) {
      widget.menu_willHighlightItem(id, sel, arg0, arg1);
    } else if (sel == OS.sel_setMarkedText_selectedRange_) {
      widget.setMarkedText_selectedRange(id, sel, arg0, arg1);
    } else if (sel == OS.sel_drawInteriorWithFrame_inView_) {
      NSRect rect = new NSRect();
      OS.memmove(rect, arg0, sizeof);
      widget.drawInteriorWithFrame_inView(id, sel, rect, arg1);
    } else if (sel == OS.sel_drawWithExpansionFrame_inView_) {
      NSRect rect = new NSRect();
      OS.memmove(rect, arg0, sizeof);
      widget.drawWithExpansionFrame_inView(id, sel, rect, arg1);
    } else if (sel == OS.sel_accessibilityAttributeValue_forParameter_) {
      return widget.accessibilityAttributeValue_forParameter(id, sel, arg0, arg1);
    } else if (sel == OS.sel_tableView_didClickTableColumn_) {
      widget.tableView_didClickTableColumn(id, sel, arg0, arg1);
    } else if (sel == OS.sel_outlineView_didClickTableColumn_) {
      widget.outlineView_didClickTableColumn(id, sel, arg0, arg1);
    } else if (sel == OS.sel_shouldChangeTextInRange_replacementString_) {
      return widget.shouldChangeTextInRange_replacementString(id, sel, arg0, arg1) ? 1 : 0;
    } else if (sel == OS.sel_canDragRowsWithIndexes_atPoint_) {
      return widget.canDragRowsWithIndexes_atPoint(id, sel, arg0, arg1) ? 1 : 0;
    } else if (sel == OS.sel_expandItem_expandChildren_) {
      widget.expandItem_expandChildren(id, sel, arg0, arg1 != 0);
    } else if (sel == OS.sel_collapseItem_collapseChildren_) {
      widget.collapseItem_collapseChildren(id, sel, arg0, arg1 != 0);
    } else if (sel == OS.sel_expansionFrameWithFrame_inView_) {
      NSRect rect = new NSRect();
      OS.memmove(rect, arg0, sizeof);
      rect = widget.expansionFrameWithFrame_inView(id, sel, rect, arg1);
      int result = OS.malloc(sizeof);
      OS.memmove(result, rect, sizeof);
      return result;
    } else if (sel == OS.sel_drawLabel_inRect_) {
      NSRect rect = new NSRect();
      OS.memmove(rect, arg1, sizeof);
      widget.drawLabelInRect(id, sel, arg0 == 1, rect);
    } else if (sel == OS.sel_scrollClipView_toPoint_) {
      NSPoint point = new NSPoint();
      OS.memmove(point, arg1, sizeof);
      widget.scrollClipViewToPoint(id, sel, arg0, point);
    } else if (sel == OS.sel_accessibilitySetValue_forAttribute_) {
      widget.accessibilitySetValue_forAttribute(id, sel, arg0, arg1);
    }
    return 0;
  }
}

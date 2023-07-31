class PlaceHold {
  int windowDelegateProc(int delegate, int sel, int arg0, int arg1) {
    int jniRef = OS.objc_msgSend(delegate, sel_tag);
    if ((jniRef == 0) || (jniRef == (-1))) {
      return 0;
    }
    Widget widget = ((Widget) (OS.JNIGetObject(jniRef)));
    if (widget == null) {
      return 0;
    }
    if (sel == OS.sel_tabView_1willSelectTabViewItem_1) {
      widget.willSelectTabViewItem(arg0, arg1);
    } else if (sel == OS.sel_outlineView_1isItemExpandable_1) {
      return widget.outlineView_isItemExpandable(arg0, arg1) ? 1 : 0;
    } else if (sel == OS.sel_outlineView_1numberOfChildrenOfItem_1) {
      return widget.outlineView_numberOfChildrenOfItem(arg0, arg1);
    } else if (sel == OS.sel_menu_1willHighlightItem_1) {
      widget.menu_willHighlightItem(arg0, arg1);
    }
    return 0;
  }
}

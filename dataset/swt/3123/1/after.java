class PlaceHold {
  static int windowDelegateProc(int id, int sel) {
    Widget widget = GetWidget(id);
    if (widget == null) {
      return 0;
    }
    if (sel == OS.sel_sendSelection) {
      widget.sendSelection();
    } else if (sel == OS.sel_sendDoubleSelection) {
      widget.sendDoubleSelection();
    } else if (sel == OS.sel_sendVerticalSelection) {
      widget.sendVerticalSelection();
    } else if (sel == OS.sel_sendHorizontalSelection) {
      widget.sendHorizontalSelection();
    } else if (sel == OS.sel_acceptsFirstResponder) {
      return widget.acceptsFirstResponder(id, sel) ? 1 : 0;
    } else if (sel == OS.sel_becomeFirstResponder) {
      return widget.becomeFirstResponder(id, sel) ? 1 : 0;
    } else if (sel == OS.sel_resignFirstResponder) {
      return widget.resignFirstResponder(id, sel) ? 1 : 0;
    } else if (sel == OS.sel_isFlipped) {
      return widget.isFlipped(id, sel) ? 1 : 0;
    } else if (sel == OS.sel_isOpaque) {
      return widget.isOpaque(id, sel) ? 1 : 0;
    } else if (sel == OS.sel_unmarkText) {
    } else if (sel == OS.sel_validAttributesForMarkedText) {
      return widget.validAttributesForMarkedText(id, sel);
    } else if (sel == OS.sel_markedRange) {
      NSRange range = widget.markedRange(id, sel);
      int result = OS.malloc(sizeof);
      OS.memmove(result, range, sizeof);
      return result;
    } else if (sel == OS.sel_selectedRange) {
      NSRange range = widget.selectedRange(id, sel);
      int result = OS.malloc(sizeof);
      OS.memmove(result, range, sizeof);
      return result;
    } else if (sel == OS.sel_hasMarkedText) {
      return widget.hasMarkedText(id, sel) ? 1 : 0;
    } else if (sel == OS.sel_canBecomeKeyWindow) {
      return widget.canBecomeKeyWindow(id, sel) ? 1 : 0;
    }
    return 0;
  }
}

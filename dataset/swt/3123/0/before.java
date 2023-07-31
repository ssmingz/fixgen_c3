class PlaceHold {
  static int windowProc(int id, int sel) {
    if (!NSThread.isMainThread()) {
      if (sel == OS.sel_isOpaque) {
        return 1;
      }
    }
    Widget widget = LookupWidget(id, sel);
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
    } else if (sel == OS.sel_sendSearchSelection) {
      widget.sendSearchSelection();
    } else if (sel == OS.sel_sendCancelSelection) {
      widget.sendCancelSelection();
    } else if (sel == OS.sel_acceptsFirstResponder) {
      return widget.acceptsFirstResponder(id, sel) ? 1 : 0;
    } else if (sel == OS.sel_becomeFirstResponder) {
      return widget.becomeFirstResponder(id, sel) ? 1 : 0;
    } else if (sel == OS.sel_resignFirstResponder) {
      return widget.resignFirstResponder(id, sel) ? 1 : 0;
    } else if (sel == OS.sel_isOpaque) {
      return widget.isOpaque(id, sel) ? 1 : 0;
    } else if (sel == OS.sel_isFlipped) {
      return widget.isFlipped(id, sel) ? 1 : 0;
    } else if (sel == OS.sel_canBecomeKeyView) {
      return widget.canBecomeKeyView(id, sel) ? 1 : 0;
    } else if (sel == OS.sel_becomeKeyWindow) {
      widget.becomeKeyWindow(id, sel);
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
    } else if (sel == OS.sel_cellSize) {
      NSSize size = widget.cellSize(id, sel);
      int result = OS.malloc(sizeof);
      OS.memmove(result, size, sizeof);
      return result;
    } else if (sel == OS.sel_hasMarkedText) {
      return widget.hasMarkedText(id, sel) ? 1 : 0;
    } else if (sel == OS.sel_canBecomeKeyWindow) {
      return widget.canBecomeKeyWindow(id, sel) ? 1 : 0;
    } else if (sel == OS.sel_accessibilityActionNames) {
      return widget.accessibilityActionNames(id, sel);
    } else if (sel == OS.sel_accessibilityAttributeNames) {
      return widget.accessibilityAttributeNames(id, sel);
    } else if (sel == OS.sel_accessibilityParameterizedAttributeNames) {
      return widget.accessibilityParameterizedAttributeNames(id, sel);
    } else if (sel == OS.sel_accessibilityFocusedUIElement) {
      return widget.accessibilityFocusedUIElement(id, sel);
    } else if (sel == OS.sel_accessibilityIsIgnored) {
      return widget.accessibilityIsIgnored(id, sel) ? 1 : 0;
    } else if (sel == OS.sel_nextState) {
      return widget.nextState(id, sel);
    } else if (sel == OS.sel_resetCursorRects) {
      widget.resetCursorRects(id, sel);
    } else if (sel == OS.sel_updateTrackingAreas) {
      widget.updateTrackingAreas(id, sel);
    } else if (sel == OS.sel_viewDidMoveToWindow) {
      widget.viewDidMoveToWindow(id, sel);
    } else if (sel == OS.sel_image) {
      return widget.image(id, sel);
    }
    return 0;
  }
}

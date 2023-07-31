class PlaceHold {
  int trayItemProc(int target, int userData, int selector, int arg0) {
    switch (selector) {
      case 0:
        {
          int mask =
              Cocoa.objc_msgSend(arg0, S_modifierFlags)
                  & Cocoa.NSDeviceIndependentModifierFlagsMask;
          if (mask == Cocoa.NSControlKeyMask) {
            showMenu();
          } else {
            highlight = true;
            Cocoa.objc_msgSend(view, S_setNeedsDisplay, 1);
            int clickCount = Cocoa.objc_msgSend(arg0, S_clickCount);
            sendSelectionEvent(clickCount == 2 ? SWT.DefaultSelection : SWT.Selection);
          }
          break;
        }
      case 1:
        {
          highlight = false;
          Cocoa.objc_msgSend(view, S_setNeedsDisplay, 1);
          break;
        }
      case 2:
        {
          showMenu();
          break;
        }
      case 3:
        {
          NSRect rect = new NSRect();
          Cocoa.memcpy(rect, arg0, sizeof);
          Cocoa.objc_msgSend(
              handle, S_drawStatusBarBackgroundInRect_withHighlight, rect, highlight ? 1 : 0);
        }
    }
    return 0;
  }
}

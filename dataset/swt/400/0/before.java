class PlaceHold {
  int kEventUnicodeKeyPressed(int nextHandler, int theEvent, int userData) {
    int result = super.kEventUnicodeKeyPressed(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    int[] keyboardEvent = new int[1];
    OS.GetEventParameter(
        theEvent,
        kEventParamTextInputSendKeyboardEvent,
        typeEventRef,
        null,
        keyboardEvent.length * 4,
        null,
        keyboardEvent);
    int[] keyCode = new int[1];
    OS.GetEventParameter(
        keyboardEvent[0], kEventParamKeyCode, typeUInt32, null, keyCode.length * 4, null, keyCode);
    switch (keyCode[0]) {
      case 126:
      case 123:
      case 125:
      case 124:
        {
          int xChange = 0;
          int yChange = 0;
          int stepSize = PAGE_INCREMENT;
          int[] modifiers = new int[1];
          OS.GetEventParameter(
              theEvent, kEventParamKeyModifiers, typeUInt32, null, 4, null, modifiers);
          if ((modifiers[0] & OS.controlKey) != 0) {
            stepSize = INCREMENT;
          }
          if ((style & SWT.VERTICAL) != 0) {
            if ((keyCode[0] == 126) || (keyCode[0] == 125)) {
              break;
            }
            xChange = (keyCode[0] == 123) ? -stepSize : stepSize;
          } else {
            if ((keyCode[0] == 123) || (keyCode[0] == 124)) {
              break;
            }
            yChange = (keyCode[0] == 126) ? -stepSize : stepSize;
          }
          Rectangle bounds = getBounds();
          int width = bounds.width;
          int height = bounds.height;
          Rectangle parentBounds = parent.getBounds();
          int parentWidth = parentBounds.width;
          int parentHeight = parentBounds.height;
          int newX = lastX;
          int newY = lastY;
          if ((style & SWT.VERTICAL) != 0) {
            newX = Math.min(Math.max(0, lastX + xChange), parentWidth - width);
          } else {
            newY = Math.min(Math.max(0, lastY + yChange), parentHeight - height);
          }
          if ((newX == lastX) && (newY == lastY)) {
            return result;
          }
          Event event = new Event();
          event.x = newX;
          event.y = newY;
          event.width = width;
          event.height = height;
          sendEvent(Selection, event);
          if (isDisposed()) {
            break;
          }
          if (event.doit) {
            setBounds(event.x, event.y, width, height);
            if (isDisposed()) {
              break;
            }
            lastX = event.x;
            lastY = event.y;
            if (isDisposed()) {
              return result;
            }
            int cursorX = event.x;
            int cursorY = event.y;
            if ((style & SWT.VERTICAL) != 0) {
              cursorY += height / 2;
            } else {
              cursorX += width / 2;
            }
            display.setCursorLocation(parent.toDisplay(cursorX, cursorY));
          }
          break;
        }
    }
    return result;
  }
}

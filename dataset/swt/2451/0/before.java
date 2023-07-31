class PlaceHold {
  boolean sendKeyEvent(NSEvent nsEvent, int type) {
    super.sendKeyEvent(nsEvent, type);
    if (type == SWT.KeyDown) {
      int keyCode = nsEvent.keyCode();
      switch (keyCode) {
        case 126:
        case 123:
        case 125:
        case 124:
          {
            int xChange = 0;
            int yChange = 0;
            int stepSize = PAGE_INCREMENT;
            int modifiers = nsEvent.modifierFlags();
            if ((modifiers & OS.NSControlKeyMask) != 0) {
              stepSize = INCREMENT;
            }
            if ((style & SWT.VERTICAL) != 0) {
              if ((keyCode == 126) || (keyCode == 125)) {
                break;
              }
              xChange = (keyCode == 123) ? -stepSize : stepSize;
            } else {
              if ((keyCode == 123) || (keyCode == 124)) {
                break;
              }
              yChange = (keyCode == 126) ? -stepSize : stepSize;
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
              return true;
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
                return false;
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
    }
    return true;
  }
}

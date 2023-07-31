class PlaceHold {
  void textEvent(Event event) {
    switch (event.type) {
      case SWT.FocusIn:
        {
          if (hasFocus) {
            return;
          }
          hasFocus = true;
          if (getEditable()) {
            text.selectAll();
          }
          Event e = new Event();
          e.time = event.time;
          notifyListeners(FocusIn, e);
          break;
        }
      case SWT.FocusOut:
        {
          event.display.asyncExec(
              new Runnable() {
                public void run() {
                  if (CCombo.this.isDisposed()) {
                    return;
                  }
                  Control focusControl = getDisplay().getFocusControl();
                  if ((focusControl == list) || (focusControl == arrow)) {
                    return;
                  }
                  hasFocus = false;
                  Event e = new Event();
                  notifyListeners(FocusOut, e);
                }
              });
          break;
        }
      case SWT.KeyDown:
        {
          if (event.character == SWT.ESC) {
            dropDown(false);
          }
          if (event.character == SWT.CR) {
            dropDown(false);
            Event e = new Event();
            e.time = event.time;
            e.stateMask = event.stateMask;
            notifyListeners(DefaultSelection, e);
          }
          if (isDisposed()) {
            break;
          }
          if ((event.keyCode == SWT.ARROW_UP) || (event.keyCode == SWT.ARROW_DOWN)) {
            int oldIndex = getSelectionIndex();
            if (event.keyCode == SWT.ARROW_UP) {
              select(Math.max(oldIndex - 1, 0));
            } else {
              select(Math.min(oldIndex + 1, getItemCount() - 1));
            }
            if (oldIndex != getSelectionIndex()) {
              Event e = new Event();
              e.time = event.time;
              e.stateMask = event.stateMask;
              notifyListeners(Selection, e);
            }
            if (isDisposed()) {
              break;
            }
          }
          Event e = new Event();
          e.time = event.time;
          e.character = event.character;
          e.keyCode = event.keyCode;
          e.stateMask = event.stateMask;
          notifyListeners(KeyDown, e);
          break;
        }
      case SWT.KeyUp:
        {
          Event e = new Event();
          e.time = event.time;
          e.character = event.character;
          e.keyCode = event.keyCode;
          e.stateMask = event.stateMask;
          notifyListeners(KeyUp, e);
          break;
        }
      case SWT.Modify:
        {
          list.deselectAll();
          Event e = new Event();
          e.time = event.time;
          notifyListeners(Modify, e);
          break;
        }
      case SWT.MouseDown:
        {
          if (event.button != 1) {
            return;
          }
          if (text.getEditable()) {
            return;
          }
          boolean dropped = isDropped();
          text.selectAll();
          if (!dropped) {
            setFocus();
          }
          dropDown(!dropped);
          break;
        }
      case SWT.MouseUp:
        {
          if (event.button != 1) {
            return;
          }
          if (text.getEditable()) {
            return;
          }
          text.selectAll();
          break;
        }
      case SWT.Traverse:
        {
          switch (event.detail) {
            case SWT.TRAVERSE_RETURN:
            case SWT.TRAVERSE_ARROW_PREVIOUS:
            case SWT.TRAVERSE_ARROW_NEXT:
              event.doit = false;
              break;
          }
          Event e = new Event();
          e.time = event.time;
          e.detail = event.detail;
          e.doit = event.doit;
          e.keyCode = event.keyCode;
          notifyListeners(Traverse, e);
          event.doit = e.doit;
          break;
        }
    }
  }
}

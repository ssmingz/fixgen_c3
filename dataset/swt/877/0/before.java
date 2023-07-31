class PlaceHold {
  void listEvent(Event event) {
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
          Control focusControl = getDisplay().getFocusControl();
          if ((focusControl == text) || (focusControl == arrow)) {
            return;
          }
          hasFocus = false;
          Event e = new Event();
          e.time = event.time;
          notifyListeners(FocusOut, e);
          break;
        }
      case SWT.MouseUp:
        {
          if (event.button != 1) {
            return;
          }
          dropDown(false);
          Event e = new Event();
          e.time = event.time;
          notifyListeners(DefaultSelection, e);
          break;
        }
      case SWT.Selection:
        {
          int index = list.getSelectionIndex();
          if (index == (-1)) {
            return;
          }
          text.setText(list.getItem(index));
          text.selectAll();
          list.setSelection(index);
          Event e = new Event();
          e.time = event.time;
          e.stateMask = event.stateMask;
          e.doit = event.doit;
          notifyListeners(Selection, e);
          event.doit = e.doit;
          break;
        }
      case SWT.Traverse:
        {
          switch (event.detail) {
            case SWT.TRAVERSE_TAB_NEXT:
            case SWT.TRAVERSE_RETURN:
            case SWT.TRAVERSE_ESCAPE:
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
      case SWT.KeyDown:
        {
          if (event.character == SWT.ESC) {
            dropDown(false);
          }
          if ((event.character == SWT.CR) || (event.character == '\t')) {
            dropDown(false);
            Event e = new Event();
            e.time = event.time;
            e.stateMask = event.stateMask;
            notifyListeners(DefaultSelection, e);
          }
          if (isDisposed()) {
            break;
          }
          Event e = new Event();
          e.time = event.time;
          e.character = event.character;
          e.keyCode = event.keyCode;
          e.stateMask = event.stateMask;
          notifyListeners(KeyDown, e);
          break;
        }
    }
  }
}

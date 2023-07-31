class PlaceHold {
  boolean sendKeyEvent(NSEvent nsEvent, int type) {
    boolean result = super.sendKeyEvent(nsEvent, type);
    if (!result) {
      return result;
    }
    if (type != SWT.KeyDown) {
      return result;
    }
    int modifierFlags = nsEvent.modifierFlags();
    if ((modifierFlags & OS.NSCommandKeyMask) != 0) {
      short keyCode = nsEvent.keyCode();
      switch (keyCode) {
        case 7:
          if ((style & SWT.PASSWORD) == 0) {
            cut();
          }
          return false;
        case 8:
          if ((style & SWT.PASSWORD) == 0) {
            copy();
          }
          return false;
        case 9:
          paste();
          return false;
        case 0:
          NSApplication.sharedApplication()
              .sendAction(sel_selectAll_, null, NSApplication.sharedApplication());
          return false;
        case 6:
          NSUndoManager undoManager = view.undoManager();
          if ((undoManager == null) && ((style & SWT.SINGLE) != 0)) {
            NSText fieldEditor = ((NSTextField) (view)).currentEditor();
            undoManager = fieldEditor.undoManager();
          }
          if (undoManager != null) {
            if ((modifierFlags & OS.NSShiftKeyMask) != 0) {
              if (undoManager.canRedo()) {
                undoManager.redo();
                return false;
              }
            } else if (undoManager.canUndo()) {
              undoManager.undo();
              return false;
            }
          }
          break;
      }
    }
    if (isDisposed()) {
      return false;
    }
    if ((style & SWT.SINGLE) != 0) {
      short keyCode = nsEvent.keyCode();
      switch (keyCode) {
        case 76:
        case 36:
          sendSelectionEvent(DefaultSelection);
      }
    }
    return result;
  }
}

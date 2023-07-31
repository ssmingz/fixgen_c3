class PlaceHold {
  void handleKey(Event event) {
    int action;
    if (event.keyCode != 0) {
      action = getKeyBinding(event.keyCode | event.stateMask);
    } else {
      action = getKeyBinding(event.character | event.stateMask);
      if (action == SWT.NULL) {
        if ((((event.stateMask & SWT.CTRL) != 0) && (event.character >= 0))
            && (event.character <= 31)) {
          int c = event.character + 64;
          action = getKeyBinding(c | event.stateMask);
        }
      }
    }
    if (action == SWT.NULL) {
      boolean ignore = false;
      if (IS_CARBON) {
        ignore =
            ((event.stateMask ^ SWT.COMMAND) == 0)
                || ((event.stateMask ^ (SWT.COMMAND | SWT.SHIFT)) == 0);
      } else if (IS_MOTIF) {
        ignore =
            ((event.stateMask ^ SWT.CTRL) == 0)
                || ((event.stateMask ^ (SWT.CTRL | SWT.SHIFT)) == 0);
      } else {
        ignore =
            ((((event.stateMask ^ SWT.ALT) == 0) || ((event.stateMask ^ SWT.CTRL) == 0))
                    || ((event.stateMask ^ (SWT.ALT | SWT.SHIFT)) == 0))
                || ((event.stateMask ^ (SWT.CTRL | SWT.SHIFT)) == 0);
      }
      if ((((((!ignore) && (event.character > 31)) && (event.character != SWT.DEL))
                  || (event.character == SWT.CR))
              || (event.character == SWT.LF))
          || (event.character == TAB)) {
        doContent(event.character);
        update();
      }
    } else {
      invokeAction(action);
    }
  }
}

class PlaceHold {
  void initializeCallbacks() {
    closures = new int[Widget.LAST_SIGNAL];
    signalIds = new int[Widget.LAST_SIGNAL];
    signalIds[Widget.BUTTON_PRESS_EVENT] =
        OS.g_signal_lookup(button_press_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.BUTTON_RELEASE_EVENT] =
        OS.g_signal_lookup(button_release_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.CONFIGURE_EVENT] = OS.g_signal_lookup(configure_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.DELETE_EVENT] = OS.g_signal_lookup(delete_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.ENTER_NOTIFY_EVENT] =
        OS.g_signal_lookup(enter_notify_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.EVENT] = OS.g_signal_lookup(event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.EVENT_AFTER] = OS.g_signal_lookup(event_after, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.EXPOSE_EVENT] = OS.g_signal_lookup(expose_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.FOCUS] = OS.g_signal_lookup(focus, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.FOCUS_IN_EVENT] = OS.g_signal_lookup(focus_in_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.FOCUS_OUT_EVENT] = OS.g_signal_lookup(focus_out_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.GRAB_FOCUS] = OS.g_signal_lookup(grab_focus, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.HIDE] = OS.g_signal_lookup(hide, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.KEY_PRESS_EVENT] = OS.g_signal_lookup(key_press_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.KEY_RELEASE_EVENT] =
        OS.g_signal_lookup(key_release_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.LEAVE_NOTIFY_EVENT] =
        OS.g_signal_lookup(leave_notify_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.MAP] = OS.g_signal_lookup(map, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.MAP_EVENT] = OS.g_signal_lookup(map_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.MNEMONIC_ACTIVATE] =
        OS.g_signal_lookup(mnemonic_activate, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.MOTION_NOTIFY_EVENT] =
        OS.g_signal_lookup(motion_notify_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.POPUP_MENU] = OS.g_signal_lookup(popup_menu, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.REALIZE] = OS.g_signal_lookup(realize, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.SCROLL_EVENT] = OS.g_signal_lookup(scroll_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.SHOW] = OS.g_signal_lookup(show, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.SHOW_HELP] = OS.g_signal_lookup(show_help, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.SIZE_ALLOCATE] = OS.g_signal_lookup(size_allocate, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.STYLE_SET] = OS.g_signal_lookup(style_set, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.UNMAP] = OS.g_signal_lookup(unmap, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.UNMAP_EVENT] = OS.g_signal_lookup(unmap_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.UNREALIZE] = OS.g_signal_lookup(realize, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.VISIBILITY_NOTIFY_EVENT] =
        OS.g_signal_lookup(visibility_notify_event, OS.GTK_TYPE_WIDGET());
    signalIds[Widget.WINDOW_STATE_EVENT] =
        OS.g_signal_lookup(window_state_event, OS.GTK_TYPE_WIDGET());
    windowCallback2 = new Callback(this, "windowProc", 2);
    windowProc2 = windowCallback2.getAddress();
    if (windowProc2 == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    closures[Widget.ACTIVATE] = OS.g_cclosure_new(windowProc2, ACTIVATE, 0);
    closures[Widget.ACTIVATE_INVERSE] = OS.g_cclosure_new(windowProc2, ACTIVATE_INVERSE, 0);
    closures[Widget.CHANGED] = OS.g_cclosure_new(windowProc2, CHANGED, 0);
    closures[Widget.CLICKED] = OS.g_cclosure_new(windowProc2, CLICKED, 0);
    closures[Widget.DAY_SELECTED] = OS.g_cclosure_new(windowProc2, DAY_SELECTED, 0);
    closures[Widget.DAY_SELECTED_DOUBLE_CLICK] =
        OS.g_cclosure_new(windowProc2, DAY_SELECTED_DOUBLE_CLICK, 0);
    closures[Widget.HIDE] = OS.g_cclosure_new(windowProc2, HIDE, 0);
    closures[Widget.GRAB_FOCUS] = OS.g_cclosure_new(windowProc2, GRAB_FOCUS, 0);
    closures[Widget.MAP] = OS.g_cclosure_new(windowProc2, MAP, 0);
    closures[Widget.MONTH_CHANGED] = OS.g_cclosure_new(windowProc2, MONTH_CHANGED, 0);
    closures[Widget.OUTPUT] = OS.g_cclosure_new(windowProc2, OUTPUT, 0);
    closures[Widget.POPUP_MENU] = OS.g_cclosure_new(windowProc2, POPUP_MENU, 0);
    closures[Widget.PREEDIT_CHANGED] = OS.g_cclosure_new(windowProc2, PREEDIT_CHANGED, 0);
    closures[Widget.REALIZE] = OS.g_cclosure_new(windowProc2, REALIZE, 0);
    closures[Widget.SELECT] = OS.g_cclosure_new(windowProc2, SELECT, 0);
    closures[Widget.SELECTION_DONE] = OS.g_cclosure_new(windowProc2, SELECTION_DONE, 0);
    closures[Widget.SHOW] = OS.g_cclosure_new(windowProc2, SHOW, 0);
    closures[Widget.START_INTERACTIVE_SEARCH] =
        OS.g_cclosure_new(windowProc2, START_INTERACTIVE_SEARCH, 0);
    closures[Widget.VALUE_CHANGED] = OS.g_cclosure_new(windowProc2, VALUE_CHANGED, 0);
    closures[Widget.UNMAP] = OS.g_cclosure_new(windowProc2, UNMAP, 0);
    closures[Widget.UNREALIZE] = OS.g_cclosure_new(windowProc2, UNREALIZE, 0);
    closures[Widget.BACKSPACE] = OS.g_cclosure_new(windowProc2, BACKSPACE, 0);
    closures[Widget.BACKSPACE_INVERSE] = OS.g_cclosure_new(windowProc2, BACKSPACE_INVERSE, 0);
    closures[Widget.COPY_CLIPBOARD] = OS.g_cclosure_new(windowProc2, COPY_CLIPBOARD, 0);
    closures[Widget.COPY_CLIPBOARD_INVERSE] =
        OS.g_cclosure_new(windowProc2, COPY_CLIPBOARD_INVERSE, 0);
    closures[Widget.CUT_CLIPBOARD] = OS.g_cclosure_new(windowProc2, CUT_CLIPBOARD, 0);
    closures[Widget.CUT_CLIPBOARD_INVERSE] =
        OS.g_cclosure_new(windowProc2, CUT_CLIPBOARD_INVERSE, 0);
    closures[Widget.PASTE_CLIPBOARD] = OS.g_cclosure_new(windowProc2, PASTE_CLIPBOARD, 0);
    closures[Widget.PASTE_CLIPBOARD_INVERSE] =
        OS.g_cclosure_new(windowProc2, PASTE_CLIPBOARD_INVERSE, 0);
    windowCallback3 = new Callback(this, "windowProc", 3);
    windowProc3 = windowCallback3.getAddress();
    if (windowProc3 == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    closures[Widget.BUTTON_PRESS_EVENT] = OS.g_cclosure_new(windowProc3, BUTTON_PRESS_EVENT, 0);
    closures[Widget.BUTTON_PRESS_EVENT_INVERSE] =
        OS.g_cclosure_new(windowProc3, BUTTON_PRESS_EVENT_INVERSE, 0);
    closures[Widget.BUTTON_RELEASE_EVENT] = OS.g_cclosure_new(windowProc3, BUTTON_RELEASE_EVENT, 0);
    closures[Widget.BUTTON_RELEASE_EVENT_INVERSE] =
        OS.g_cclosure_new(windowProc3, BUTTON_RELEASE_EVENT_INVERSE, 0);
    closures[Widget.COMMIT] = OS.g_cclosure_new(windowProc3, COMMIT, 0);
    closures[Widget.CONFIGURE_EVENT] = OS.g_cclosure_new(windowProc3, CONFIGURE_EVENT, 0);
    closures[Widget.DELETE_EVENT] = OS.g_cclosure_new(windowProc3, DELETE_EVENT, 0);
    closures[Widget.ENTER_NOTIFY_EVENT] = OS.g_cclosure_new(windowProc3, ENTER_NOTIFY_EVENT, 0);
    closures[Widget.EVENT] = OS.g_cclosure_new(windowProc3, EVENT, 0);
    closures[Widget.EVENT_AFTER] = OS.g_cclosure_new(windowProc3, EVENT_AFTER, 0);
    closures[Widget.EXPOSE_EVENT] = OS.g_cclosure_new(windowProc3, EXPOSE_EVENT, 0);
    closures[Widget.EXPOSE_EVENT_INVERSE] = OS.g_cclosure_new(windowProc3, EXPOSE_EVENT_INVERSE, 0);
    closures[Widget.FOCUS] = OS.g_cclosure_new(windowProc3, FOCUS, 0);
    closures[Widget.FOCUS_IN_EVENT] = OS.g_cclosure_new(windowProc3, FOCUS_IN_EVENT, 0);
    closures[Widget.FOCUS_OUT_EVENT] = OS.g_cclosure_new(windowProc3, FOCUS_OUT_EVENT, 0);
    closures[Widget.KEY_PRESS_EVENT] = OS.g_cclosure_new(windowProc3, KEY_PRESS_EVENT, 0);
    closures[Widget.KEY_RELEASE_EVENT] = OS.g_cclosure_new(windowProc3, KEY_RELEASE_EVENT, 0);
    closures[Widget.INPUT] = OS.g_cclosure_new(windowProc3, INPUT, 0);
    closures[Widget.LEAVE_NOTIFY_EVENT] = OS.g_cclosure_new(windowProc3, LEAVE_NOTIFY_EVENT, 0);
    closures[Widget.MAP_EVENT] = OS.g_cclosure_new(windowProc3, MAP_EVENT, 0);
    closures[Widget.MNEMONIC_ACTIVATE] = OS.g_cclosure_new(windowProc3, MNEMONIC_ACTIVATE, 0);
    closures[Widget.MOTION_NOTIFY_EVENT] = OS.g_cclosure_new(windowProc3, MOTION_NOTIFY_EVENT, 0);
    closures[Widget.MOTION_NOTIFY_EVENT_INVERSE] =
        OS.g_cclosure_new(windowProc3, MOTION_NOTIFY_EVENT_INVERSE, 0);
    closures[Widget.MOVE_FOCUS] = OS.g_cclosure_new(windowProc3, MOVE_FOCUS, 0);
    closures[Widget.POPULATE_POPUP] = OS.g_cclosure_new(windowProc3, POPULATE_POPUP, 0);
    closures[Widget.SCROLL_EVENT] = OS.g_cclosure_new(windowProc3, SCROLL_EVENT, 0);
    closures[Widget.SHOW_HELP] = OS.g_cclosure_new(windowProc3, SHOW_HELP, 0);
    closures[Widget.SIZE_ALLOCATE] = OS.g_cclosure_new(windowProc3, SIZE_ALLOCATE, 0);
    closures[Widget.STYLE_SET] = OS.g_cclosure_new(windowProc3, STYLE_SET, 0);
    closures[Widget.TOGGLED] = OS.g_cclosure_new(windowProc3, TOGGLED, 0);
    closures[Widget.UNMAP_EVENT] = OS.g_cclosure_new(windowProc3, UNMAP_EVENT, 0);
    closures[Widget.VISIBILITY_NOTIFY_EVENT] =
        OS.g_cclosure_new(windowProc3, VISIBILITY_NOTIFY_EVENT, 0);
    closures[Widget.WINDOW_STATE_EVENT] = OS.g_cclosure_new(windowProc3, WINDOW_STATE_EVENT, 0);
    closures[Widget.ROW_DELETED] = OS.g_cclosure_new(windowProc3, ROW_DELETED, 0);
    closures[Widget.DIRECTION_CHANGED] = OS.g_cclosure_new(windowProc3, DIRECTION_CHANGED, 0);
    windowCallback4 = new Callback(this, "windowProc", 4);
    windowProc4 = windowCallback4.getAddress();
    if (windowProc4 == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    closures[Widget.DELETE_RANGE] = OS.g_cclosure_new(windowProc4, DELETE_RANGE, 0);
    closures[Widget.DELETE_TEXT] = OS.g_cclosure_new(windowProc4, DELETE_TEXT, 0);
    closures[Widget.ICON_RELEASE] = OS.g_cclosure_new(windowProc4, ICON_RELEASE, 0);
    closures[Widget.ROW_ACTIVATED] = OS.g_cclosure_new(windowProc4, ROW_ACTIVATED, 0);
    closures[Widget.SCROLL_CHILD] = OS.g_cclosure_new(windowProc4, SCROLL_CHILD, 0);
    closures[Widget.STATUS_ICON_POPUP_MENU] =
        OS.g_cclosure_new(windowProc4, STATUS_ICON_POPUP_MENU, 0);
    closures[Widget.SWITCH_PAGE] = OS.g_cclosure_new(windowProc4, SWITCH_PAGE, 0);
    closures[Widget.TEST_COLLAPSE_ROW] = OS.g_cclosure_new(windowProc4, TEST_COLLAPSE_ROW, 0);
    closures[Widget.TEST_EXPAND_ROW] = OS.g_cclosure_new(windowProc4, TEST_EXPAND_ROW, 0);
    closures[Widget.ROW_INSERTED] = OS.g_cclosure_new(windowProc4, ROW_INSERTED, 0);
    closures[Widget.DELETE_FROM_CURSOR] = OS.g_cclosure_new(windowProc4, DELETE_FROM_CURSOR, 0);
    closures[Widget.DELETE_FROM_CURSOR_INVERSE] =
        OS.g_cclosure_new(windowProc4, DELETE_FROM_CURSOR_INVERSE, 0);
    windowCallback5 = new Callback(this, "windowProc", 5);
    windowProc5 = windowCallback5.getAddress();
    if (windowProc5 == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    closures[Widget.CHANGE_VALUE] = OS.g_cclosure_new(windowProc5, CHANGE_VALUE, 0);
    closures[Widget.EXPAND_COLLAPSE_CURSOR_ROW] =
        OS.g_cclosure_new(windowProc5, EXPAND_COLLAPSE_CURSOR_ROW, 0);
    closures[Widget.INSERT_TEXT] = OS.g_cclosure_new(windowProc5, INSERT_TEXT, 0);
    closures[Widget.TEXT_BUFFER_INSERT_TEXT] =
        OS.g_cclosure_new(windowProc5, TEXT_BUFFER_INSERT_TEXT, 0);
    closures[Widget.MOVE_CURSOR] = OS.g_cclosure_new(windowProc5, MOVE_CURSOR, 0);
    closures[Widget.MOVE_CURSOR_INVERSE] = OS.g_cclosure_new(windowProc5, MOVE_CURSOR_INVERSE, 0);
    for (int i = 0; i < Widget.LAST_SIGNAL; i++) {
      if (closures[i] != 0) {
        OS.g_closure_ref(closures[i]);
      }
    }
    timerCallback = new Callback(this, "timerProc", 1);
    timerProc = timerCallback.getAddress();
    if (timerProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    windowTimerCallback = new Callback(this, "windowTimerProc", 1);
    windowTimerProc = windowTimerCallback.getAddress();
    if (windowTimerProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    mouseHoverCallback = new Callback(this, "mouseHoverProc", 1);
    mouseHoverProc = mouseHoverCallback.getAddress();
    if (mouseHoverProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    caretCallback = new Callback(this, "caretProc", 1);
    caretProc = caretCallback.getAddress();
    if (caretProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    menuPositionCallback = new Callback(this, "menuPositionProc", 5);
    menuPositionProc = menuPositionCallback.getAddress();
    if (menuPositionProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    sizeAllocateCallback = new Callback(this, "sizeAllocateProc", 3);
    sizeAllocateProc = sizeAllocateCallback.getAddress();
    if (sizeAllocateProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    sizeRequestCallback = new Callback(this, "sizeRequestProc", 3);
    sizeRequestProc = sizeRequestCallback.getAddress();
    if (sizeRequestProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    shellMapCallback = new Callback(this, "shellMapProc", 3);
    shellMapProc = shellMapCallback.getAddress();
    if (shellMapProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    shellMapProcClosure = OS.g_cclosure_new(shellMapProc, 0, 0);
    OS.g_closure_ref(shellMapProcClosure);
    cellDataCallback = new Callback(this, "cellDataProc", 5);
    cellDataProc = cellDataCallback.getAddress();
    if (cellDataProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    setDirectionCallback = new Callback(this, "setDirectionProc", 2);
    setDirectionProc = setDirectionCallback.getAddress();
    if (setDirectionProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    emissionProcCallback = new Callback(this, "emissionProc", 4);
    emissionProc = emissionProcCallback.getAddress();
    if (emissionProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    allChildrenCallback = new Callback(this, "allChildrenProc", 2);
    allChildrenProc = allChildrenCallback.getAddress();
    if (allChildrenProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    checkIfEventCallback = new Callback(this, "checkIfEventProc", 3);
    checkIfEventProc = checkIfEventCallback.getAddress();
    if (checkIfEventProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
    idleCallback = new Callback(this, "idleProc", 1);
    idleProc = idleCallback.getAddress();
    if (idleProc == 0) {
      error(ERROR_NO_MORE_CALLBACKS);
    }
  }
}

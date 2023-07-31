class PlaceHold {
  long windowProc(long handle, long arg0, long user_data) {
    switch (((int) (user_data))) {
      case EXPOSE_EVENT_INVERSE:
        {
          GdkEventExpose gdkEvent = new GdkEventExpose();
          OS.memmove(gdkEvent, arg0, sizeof);
          long paintWindow = paintWindow();
          long window = gdkEvent.window;
          if (window != paintWindow) {
            return 0;
          }
          return (state & OBSCURED) != 0 ? 1 : 0;
        }
      case BUTTON_PRESS_EVENT_INVERSE:
      case BUTTON_RELEASE_EVENT_INVERSE:
      case MOTION_NOTIFY_EVENT_INVERSE:
        {
          return 1;
        }
      case BUTTON_PRESS_EVENT:
        return gtk_button_press_event(handle, arg0);
      case BUTTON_RELEASE_EVENT:
        return gtk_button_release_event(handle, arg0);
      case COMMIT:
        return gtk_commit(handle, arg0);
      case CONFIGURE_EVENT:
        return gtk_configure_event(handle, arg0);
      case DELETE_EVENT:
        return gtk_delete_event(handle, arg0);
      case ENTER_NOTIFY_EVENT:
        return gtk_enter_notify_event(handle, arg0);
      case EVENT:
        return gtk_event(handle, arg0);
      case EVENT_AFTER:
        return gtk_event_after(handle, arg0);
      case EXPOSE_EVENT:
        {
          if (OS.GTK3) {
            return gtk_draw(handle, arg0);
          } else {
            return gtk_expose_event(handle, arg0);
          }
        }
      case FOCUS:
        return gtk_focus(handle, arg0);
      case FOCUS_IN_EVENT:
        return gtk_focus_in_event(handle, arg0);
      case FOCUS_OUT_EVENT:
        return gtk_focus_out_event(handle, arg0);
      case KEY_PRESS_EVENT:
        return gtk_key_press_event(handle, arg0);
      case KEY_RELEASE_EVENT:
        return gtk_key_release_event(handle, arg0);
      case INPUT:
        return gtk_input(handle, arg0);
      case LEAVE_NOTIFY_EVENT:
        return gtk_leave_notify_event(handle, arg0);
      case MAP_EVENT:
        return gtk_map_event(handle, arg0);
      case MNEMONIC_ACTIVATE:
        return gtk_mnemonic_activate(handle, arg0);
      case MOTION_NOTIFY_EVENT:
        return gtk_motion_notify_event(handle, arg0);
      case MOVE_FOCUS:
        return gtk_move_focus(handle, arg0);
      case POPULATE_POPUP:
        return gtk_populate_popup(handle, arg0);
      case SCROLL_EVENT:
        return gtk_scroll_event(handle, arg0);
      case SHOW_HELP:
        return gtk_show_help(handle, arg0);
      case SIZE_ALLOCATE:
        return gtk_size_allocate(handle, arg0);
      case STYLE_SET:
        return gtk_style_set(handle, arg0);
      case TOGGLED:
        return gtk_toggled(handle, arg0);
      case UNMAP_EVENT:
        return gtk_unmap_event(handle, arg0);
      case VISIBILITY_NOTIFY_EVENT:
        return gtk_visibility_notify_event(handle, arg0);
      case WINDOW_STATE_EVENT:
        return gtk_window_state_event(handle, arg0);
      case ROW_DELETED:
        return gtk_row_deleted(handle, arg0);
      default:
        return 0;
    }
  }
}

class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | MENU;
    if ((style & SWT.READ_ONLY) != 0) {
      if ((style & ((SWT.BORDER | SWT.H_SCROLL) | SWT.V_SCROLL)) == 0) {
        state |= THEME_BACKGROUND;
      }
    }
    fixedHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (fixedHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    gtk_widget_set_has_window(fixedHandle, true);
    if ((style & SWT.SINGLE) != 0) {
      handle = OS.gtk_entry_new();
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_container_add(fixedHandle, handle);
      OS.gtk_editable_set_editable(handle, (style & SWT.READ_ONLY) == 0);
      OS.gtk_entry_set_has_frame(handle, (style & SWT.BORDER) != 0);
      OS.gtk_entry_set_visibility(handle, (style & SWT.PASSWORD) == 0);
      float alignment = 0.0F;
      if ((style & SWT.CENTER) != 0) {
        alignment = 0.5F;
      }
      if ((style & SWT.RIGHT) != 0) {
        alignment = 1.0F;
      }
      if (alignment > 0.0F) {
        OS.gtk_entry_set_alignment(handle, alignment);
      }
    } else {
      scrolledHandle = OS.gtk_scrolled_window_new(0, 0);
      if (scrolledHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      handle = OS.gtk_text_view_new();
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      bufferHandle = OS.gtk_text_view_get_buffer(handle);
      if (bufferHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_container_add(fixedHandle, scrolledHandle);
      OS.gtk_container_add(scrolledHandle, handle);
      OS.gtk_text_view_set_editable(handle, (style & SWT.READ_ONLY) == 0);
      if ((style & SWT.WRAP) != 0) {
        OS.gtk_text_view_set_wrap_mode(handle, GTK_WRAP_WORD_CHAR);
      }
      int hsp = ((style & SWT.H_SCROLL) != 0) ? OS.GTK_POLICY_ALWAYS : OS.GTK_POLICY_NEVER;
      int vsp = ((style & SWT.V_SCROLL) != 0) ? OS.GTK_POLICY_ALWAYS : OS.GTK_POLICY_NEVER;
      OS.gtk_scrolled_window_set_policy(scrolledHandle, hsp, vsp);
      if ((style & SWT.BORDER) != 0) {
        OS.gtk_scrolled_window_set_shadow_type(scrolledHandle, GTK_SHADOW_ETCHED_IN);
      }
      int just = OS.GTK_JUSTIFY_LEFT;
      if ((style & SWT.CENTER) != 0) {
        just = OS.GTK_JUSTIFY_CENTER;
      }
      if ((style & SWT.RIGHT) != 0) {
        just = OS.GTK_JUSTIFY_RIGHT;
      }
      OS.gtk_text_view_set_justification(handle, just);
    }
  }
}

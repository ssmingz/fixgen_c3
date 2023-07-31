class PlaceHold {
  boolean grab() {
    long cursor = (this.cursor != null) ? this.cursor.handle : 0;
    int result =
        gdk_pointer_grab(
            window,
            GDK_OWNERSHIP_NONE,
            false,
            OS.GDK_POINTER_MOTION_MASK | OS.GDK_BUTTON_RELEASE_MASK,
            window,
            cursor,
            GDK_CURRENT_TIME);
    return result == OS.GDK_GRAB_SUCCESS;
  }
}

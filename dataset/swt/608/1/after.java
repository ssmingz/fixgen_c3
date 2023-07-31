class PlaceHold {
  boolean setScrollBarVisible(ScrollBar bar, boolean visible) {
    if (scrolledHandle == 0) {
      return false;
    }
    int[] hsp = new int[1];
    int[] vsp = new int[1];
    OS.gtk_scrolled_window_get_policy(scrolledHandle, hsp, vsp);
    int policy = (visible) ? OS.GTK_POLICY_ALWAYS : OS.GTK_POLICY_NEVER;
    if ((style & SWT.HORIZONTAL) != 0) {
      if (hsp[0] == policy) {
        return false;
      }
      hsp[0] = policy;
    } else {
      if (vsp[0] == policy) {
        return false;
      }
      vsp[0] = policy;
    }
    OS.gtk_scrolled_window_set_policy(scrolledHandle, hsp[0], vsp[0]);
    OS.gtk_container_resize_children(scrolledHandle);
    bar.sendEvent(visible ? SWT.Show : SWT.Hide);
    sendEvent(Resize);
    return true;
  }
}

class PlaceHold {
  void removeTabControl(Control control, boolean update) {
    if ((control != null) && (control.getParent() != this)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int index = -1;
    for (int i = 0; i < controls.length; i++) {
      if (controls[i] == control) {
        index = i;
        break;
      }
    }
    if (index == (-1)) {
      return;
    }
    if (!control.isDisposed()) {
      control.removeListener(Resize, listener);
      control.setBackground(null);
      control.setBackgroundImage(null);
      if (control instanceof Composite) {
        ((Composite) (control)).setBackgroundMode(INHERIT_NONE);
      }
    }
    if ((controlBkImages[index] != null) && (!controlBkImages[index].isDisposed())) {
      controlBkImages[index].dispose();
    }
    if (controls.length == 1) {
      controls = new Control[0];
      controlAlignments = new int[0];
      controlRects = new Rectangle[0];
      controlBkImages = new Image[0];
    } else {
      Control[] newControls = new Control[controls.length - 1];
      System.arraycopy(controls, 0, newControls, 0, index);
      System.arraycopy(controls, index + 1, newControls, index, (controls.length - index) - 1);
      controls = newControls;
      int[] newAlignments = new int[controls.length];
      System.arraycopy(controlAlignments, 0, newAlignments, 0, index);
      System.arraycopy(controlAlignments, index + 1, newAlignments, index, controls.length - index);
      controlAlignments = newAlignments;
      Rectangle[] newRects = new Rectangle[controls.length];
      System.arraycopy(controlRects, 0, newRects, 0, index);
      System.arraycopy(controlRects, index + 1, newRects, index, controls.length - index);
      controlRects = newRects;
      Image[] newBkImages = new Image[controls.length];
      System.arraycopy(controlBkImages, 0, newBkImages, 0, index);
      System.arraycopy(controlBkImages, index + 1, newBkImages, index, controls.length - index);
      controlBkImages = newBkImages;
    }
    if (update) {
      updateFolder(UPDATE_TAB_HEIGHT | REDRAW);
    }
  }
}

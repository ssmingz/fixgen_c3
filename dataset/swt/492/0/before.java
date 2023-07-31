class PlaceHold {
  void addTabControl(Control control, int flags, int index, boolean update) {
    switch (flags) {
      case SWT.TRAIL:
      case SWT.TRAIL | SWT.WRAP:
      case SWT.TRAIL | SWT.FILL:
      case (SWT.TRAIL | SWT.FILL) | SWT.WRAP:
      case SWT.LEAD:
        break;
      default:
        SWT.error(ERROR_INVALID_ARGUMENT);
        break;
    }
    if ((control != null) && (control.getParent() != this)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    for (int i = 0; i < controls.length; i++) {
      if (controls[i] == control) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
    }
    int length = controls.length;
    control.addListener(Resize, listener);
    Control[] newControls = new Control[length + 1];
    System.arraycopy(controls, 0, newControls, 0, length);
    controls = newControls;
    int[] newAlignment = new int[length + 1];
    System.arraycopy(controlAlignments, 0, newAlignment, 0, length);
    controlAlignments = newAlignment;
    Rectangle[] newRect = new Rectangle[length + 1];
    System.arraycopy(controlRects, 0, newRect, 0, length);
    controlRects = newRect;
    Image[] newImage = new Image[length + 1];
    System.arraycopy(controlBkImages, 0, newImage, 0, length);
    controlBkImages = newImage;
    if (index == (-1)) {
      index = length;
      if ((chevronTb != null) && (control != chevronTb)) {
        index--;
      }
    }
    System.arraycopy(controls, index, controls, index + 1, length - index);
    System.arraycopy(controlAlignments, index, controlAlignments, index + 1, length - index);
    System.arraycopy(controlRects, index, controlRects, index + 1, length - index);
    System.arraycopy(controlBkImages, index, controlBkImages, index + 1, length - index);
    controls[index] = control;
    controlAlignments[index] = flags;
    controlRects[index] = new Rectangle(0, 0, 0, 0);
    if (update) {
      updateTabHeight(false);
      if (updateItems()) {
        redraw();
      }
      updateBkImages();
    }
  }
}

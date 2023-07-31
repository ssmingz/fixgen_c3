class PlaceHold {
  void releaseChildren(boolean destroy) {
    Shell[] shells = getShells();
    for (int i = 0; i < shells.length; i++) {
      Shell shell = shells[i];
      if ((shell != null) && (!shell.isDisposed())) {
        shell.release((style & SWT.ON_TOP) != 0);
      }
    }
    super.releaseChildren(destroy);
  }
}

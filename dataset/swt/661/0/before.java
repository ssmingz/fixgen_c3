class PlaceHold {
  void fillAccel(ACCEL accel) {
    accel.fVirt = 0;
    accel.cmd = accel.key = 0;
    if ((accelerator == 0) || (!getEnabled())) {
      return;
    }
    int fVirt = OS.FVIRTKEY;
    int key = accelerator & SWT.KEY_MASK;
    int vKey = Display.untranslateKey(key);
    if (vKey != 0) {
      key = vKey;
    } else {
      switch (key) {
        case 27:
          key = OS.VK_ESCAPE;
          break;
        case 127:
          key = OS.VK_DELETE;
          break;
        default:
          {
            key = Display.wcsToMbcs(((char) (key)));
            if (key == 0) {
              return;
            }
            if (OS.IsWinCE) {
              key = OS.CharUpper(((short) (key)));
            } else {
              vKey = OS.VkKeyScan(((short) (key))) & 0xff;
              if (vKey == (-1)) {
                fVirt = 0;
              } else {
                key = vKey;
              }
            }
          }
      }
    }
    accel.key = ((short) (key));
    accel.cmd = ((short) (id));
    accel.fVirt = ((byte) (fVirt));
    if ((accelerator & SWT.ALT) != 0) {
      accel.fVirt |= OS.FALT;
    }
    if ((accelerator & SWT.SHIFT) != 0) {
      accel.fVirt |= OS.FSHIFT;
    }
    if ((accelerator & SWT.CONTROL) != 0) {
      accel.fVirt |= OS.FCONTROL;
    }
  }
}

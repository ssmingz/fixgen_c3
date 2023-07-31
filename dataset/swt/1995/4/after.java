class PlaceHold {
  int traversalCode(int key_sym, PhKeyEvent_t ke) {
    int code = super.traversalCode(key_sym, ke);
    if ((style & SWT.READ_ONLY) != 0) {
      return code;
    }
    if ((style & SWT.MULTI) != 0) {
      code &= ~SWT.TRAVERSE_RETURN;
      if ((key_sym == OS.Pk_Tab) && (ke != null)) {
        if ((ke.key_mods & OS.Pk_KM_Ctrl) == 0) {
          code &= ~(SWT.TRAVERSE_TAB_NEXT | SWT.TRAVERSE_TAB_PREVIOUS);
        }
      }
    }
    return code;
  }
}

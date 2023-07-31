class PlaceHold {
  int windowProc(int handle, int user_data) {
    switch (((int) (user_data))) {
      case BACKSPACE:
      case COPY_CLIPBOARD:
      case CUT_CLIPBOARD:
      case PASTE_CLIPBOARD:
        {
          if (segments != null) {
            clearSegments(true);
          }
          break;
        }
      case BACKSPACE_INVERSE:
      case COPY_CLIPBOARD_INVERSE:
      case CUT_CLIPBOARD_INVERSE:
      case PASTE_CLIPBOARD_INVERSE:
        {
          applySegments();
          return 0;
        }
    }
    return super.windowProc(handle, user_data);
  }
}

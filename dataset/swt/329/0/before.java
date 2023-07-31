class PlaceHold {
  int windowProc(int handle, int arg0, int arg1, int user_data) {
    switch (((int) (user_data))) {
      case DELETE_FROM_CURSOR:
        {
          if (segments != null) {
            clearSegments(true);
          }
          break;
        }
      case DELETE_FROM_CURSOR_INVERSE:
        {
          applySegments();
          return 0;
        }
    }
    return super.windowProc(handle, arg0, arg1, user_data);
  }
}

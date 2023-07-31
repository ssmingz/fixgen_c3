class PlaceHold {
  int windowProc(int handle, int arg0, int user_data) {
    if (hooks(GetSegments) || filters(GetSegments)) {
      switch (((int) (user_data))) {
        case DIRECTION_CHANGED:
          {
            if (segments != null) {
              clearSegments(true);
            }
            applySegments();
            return 0;
          }
      }
    }
    return super.windowProc(handle, arg0, user_data);
  }
}

class PlaceHold {
  boolean translateTraversal(int key, int theEvent) {
    int detail = SWT.TRAVERSE_NONE;
    int code = traversalCode(key, theEvent);
    boolean all = false;
    switch (key) {
      case 53:
        {
          all = true;
          detail = SWT.TRAVERSE_ESCAPE;
          break;
        }
      case 36:
        {
          all = true;
          detail = SWT.TRAVERSE_RETURN;
          break;
        }
      case 48:
        {
          int[] modifiers = new int[1];
          OS.GetEventParameter(
              theEvent, kEventParamKeyModifiers, typeUInt32, null, 4, null, modifiers);
          boolean next = (modifiers[0] & OS.shiftKey) == 0;
          detail = (next) ? SWT.TRAVERSE_TAB_NEXT : SWT.TRAVERSE_TAB_PREVIOUS;
          break;
        }
      case 126:
      case 123:
      case 125:
      case 124:
        {
          boolean next = (key == 125) || (key == 124);
          detail = (next) ? SWT.TRAVERSE_ARROW_NEXT : SWT.TRAVERSE_ARROW_PREVIOUS;
          break;
        }
      case 116:
      case 121:
        {
          all = true;
          int[] modifiers = new int[1];
          OS.GetEventParameter(
              theEvent, kEventParamKeyModifiers, typeUInt32, null, 4, null, modifiers);
          if ((modifiers[0] & OS.controlKey) == 0) {
            return false;
          }
          detail = (key == 121) ? SWT.TRAVERSE_PAGE_NEXT : SWT.TRAVERSE_PAGE_PREVIOUS;
          break;
        }
      default:
        return false;
    }
    Event event = new Event();
    event.doit = (code & detail) != 0;
    event.detail = detail;
    if (!setKeyState(event, theEvent)) {
      return false;
    }
    Shell shell = getShell();
    Control control = this;
    do {
      if (control.traverse(event)) {
        return true;
      }
      if ((!event.doit) && control.hooks(Traverse)) {
        return false;
      }
      if (control == shell) {
        return false;
      }
      control = control.parent;
    } while (all && (control != null));
    return false;
  }
}

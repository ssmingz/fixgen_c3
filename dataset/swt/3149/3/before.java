class PlaceHold {
  void setZOrder(Control sibling, boolean above, boolean fixRelations, boolean fixChildren) {
    int index = 0;
    int siblingIndex = 0;
    int oldNextIndex = -1;
    Control[] children = null;
    if (fixRelations) {
      children = parent._getChildren();
      while (index < children.length) {
        if (children[index] == this) {
          break;
        }
        index++;
      }
      if (sibling != null) {
        while (siblingIndex < children.length) {
          if (children[siblingIndex] == sibling) {
            break;
          }
          siblingIndex++;
        }
      }
      removeRelation();
      if ((index + 1) < children.length) {
        oldNextIndex = index + 1;
        children[oldNextIndex].removeRelation();
      }
      if (sibling != null) {
        if (above) {
          sibling.removeRelation();
        } else if ((siblingIndex + 1) < children.length) {
          children[siblingIndex + 1].removeRelation();
        }
      }
    }
    int topHandle = topHandle();
    int siblingHandle = (sibling != null) ? sibling.topHandle() : 0;
    int window = OS.GTK_WIDGET_WINDOW(topHandle);
    if (window != 0) {
      int siblingWindow = 0;
      if (sibling != null) {
        if (above && (sibling.enableWindow != 0)) {
          siblingWindow = enableWindow;
        } else {
          siblingWindow = OS.GTK_WIDGET_WINDOW(siblingHandle);
        }
      }
      int redrawWindow = (fixChildren) ? parent.redrawWindow : 0;
      if ((!OS.GDK_WINDOWING_X11())
          || ((siblingWindow == 0) && ((!above) || (redrawWindow == 0)))) {
        if (above) {
          OS.gdk_window_raise(window);
          if (redrawWindow != 0) {
            OS.gdk_window_raise(redrawWindow);
          }
          if (enableWindow != 0) {
            OS.gdk_window_raise(enableWindow);
          }
        } else {
          if (enableWindow != 0) {
            OS.gdk_window_lower(enableWindow);
          }
          OS.gdk_window_lower(window);
        }
      } else {
        int siblingW = (siblingWindow != 0) ? siblingWindow : redrawWindow;
        boolean stack_mode = above;
        if ((redrawWindow != 0) && (siblingWindow == 0)) {
          stack_mode = false;
        }
        restackWindow(window, siblingW, stack_mode);
        if (enableWindow != 0) {
          restackWindow(enableWindow, window, true);
        }
      }
    }
    if (fixChildren) {
      if (above) {
        parent.moveAbove(topHandle, siblingHandle);
      } else {
        parent.moveBelow(topHandle, siblingHandle);
      }
    }
    if ((!above) && fixChildren) {
      parent.fixZOrder();
    }
    if (fixRelations) {
      if (sibling != null) {
        if (above) {
          index = siblingIndex - (index < siblingIndex ? 1 : 0);
        } else {
          index = siblingIndex + (siblingIndex < index ? 1 : 0);
        }
      } else if (above) {
        index = 0;
      } else {
        index = children.length - 1;
      }
      children = parent._getChildren();
      if (0 < index) {
        children[index - 1].addRelation(this);
      }
      if ((index + 1) < children.length) {
        addRelation(children[index + 1]);
      }
      if (oldNextIndex != (-1)) {
        if (oldNextIndex <= index) {
          oldNextIndex--;
        }
        if (((0 < oldNextIndex) && (oldNextIndex != index)) && (oldNextIndex != (index + 1))) {
          children[oldNextIndex - 1].addRelation(children[oldNextIndex]);
        }
      }
    }
  }
}

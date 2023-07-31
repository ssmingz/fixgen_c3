class PlaceHold {
  static int atkObject_ref_state_set(int atkObject) {
    if (DEBUG) {
      print("-->atkObject_ref_state_set: " + atkObject);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    int parentResult = 0;
    AtkObjectClass objectClass = getObjectClass(atkObject);
    if (objectClass.ref_state_set != 0) {
      parentResult = ATK.call(objectClass.ref_state_set, atkObject);
    }
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleControlListeners;
      int length = listeners.size();
      if (length > 0) {
        int set = parentResult;
        AccessibleControlEvent event = new AccessibleControlEvent(accessible);
        event.childID = object.id;
        event.detail = -1;
        for (int i = 0; i < length; i++) {
          AccessibleControlListener listener =
              ((AccessibleControlListener) (listeners.elementAt(i)));
          listener.getState(event);
        }
        if (event.detail != (-1)) {
          int state = event.detail;
          if ((state & ACC.STATE_BUSY) != 0) {
            ATK.atk_state_set_add_state(set, ATK_STATE_BUSY);
          }
          if ((state & ACC.STATE_CHECKED) != 0) {
            ATK.atk_state_set_add_state(set, ATK_STATE_CHECKED);
          }
          if ((state & ACC.STATE_DISABLED) == 0) {
            ATK.atk_state_set_add_state(set, ATK_STATE_ENABLED);
          }
          if ((state & ACC.STATE_EXPANDED) != 0) {
            ATK.atk_state_set_add_state(set, ATK_STATE_EXPANDED);
          }
          if ((state & ACC.STATE_FOCUSABLE) != 0) {
            ATK.atk_state_set_add_state(set, ATK_STATE_FOCUSABLE);
          }
          if ((state & ACC.STATE_FOCUSED) != 0) {
            ATK.atk_state_set_add_state(set, ATK_STATE_FOCUSED);
          }
          if ((state & ACC.STATE_HOTTRACKED) != 0) {
            ATK.atk_state_set_add_state(set, ATK_STATE_ARMED);
          }
          if ((state & ACC.STATE_INVISIBLE) == 0) {
            ATK.atk_state_set_add_state(set, ATK_STATE_VISIBLE);
          }
          if ((state & ACC.STATE_MULTISELECTABLE) != 0) {
            ATK.atk_state_set_add_state(set, ATK_STATE_MULTISELECTABLE);
          }
          if ((state & ACC.STATE_OFFSCREEN) == 0) {
            ATK.atk_state_set_add_state(set, ATK_STATE_SHOWING);
          }
          if ((state & ACC.STATE_PRESSED) != 0) {
            ATK.atk_state_set_add_state(set, ATK_STATE_PRESSED);
          }
          if ((state & ACC.STATE_READONLY) == 0) {
            ATK.atk_state_set_add_state(set, ATK_STATE_EDITABLE);
          }
          if ((state & ACC.STATE_SELECTABLE) != 0) {
            ATK.atk_state_set_add_state(set, ATK_STATE_SELECTABLE);
          }
          if ((state & ACC.STATE_SELECTED) != 0) {
            ATK.atk_state_set_add_state(set, ATK_STATE_SELECTED);
          }
          if ((state & ACC.STATE_SIZEABLE) != 0) {
            ATK.atk_state_set_add_state(set, ATK_STATE_RESIZABLE);
          }
        }
        return set;
      }
    }
    return parentResult;
  }
}

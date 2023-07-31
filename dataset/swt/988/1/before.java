class PlaceHold {
  static int atkObject_ref_state_set(int atkObject) {
    if (DEBUG) {
      System.out.println("-->atkObject_ref_state_set");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    int parentResult = 0;
    int superType = ATK.g_type_class_peek(object.parentType);
    AtkObjectClass objectClass = new AtkObjectClass();
    ATK.memmove(objectClass, superType);
    if (objectClass.ref_state_set != 0) {
      parentResult = ATK.call(objectClass.ref_state_set, object.handle);
    }
    AccessibleControlListener[] listeners = object.getControlListeners();
    if (listeners.length == 0) {
      return parentResult;
    }
    int set = parentResult;
    AccessibleControlEvent event = new AccessibleControlEvent(object);
    event.childID = object.id;
    event.detail = -1;
    for (int i = 0; i < listeners.length; i++) {
      listeners[i].getState(event);
    }
    if (event.detail != (-1)) {
      int state = event.detail;
      if ((state & ACC.STATE_BUSY) != 0) {
        ATK.atk_state_set_add_state(set, ATK_STATE_BUSY);
      }
      if ((state & ACC.STATE_CHECKED) != 0) {
        ATK.atk_state_set_add_state(set, ATK_STATE_CHECKED);
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

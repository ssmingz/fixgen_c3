class PlaceHold {
  int initActionIfaceCB(int iface) {
    AtkActionIface actionIface = new AtkActionIface();
    ATK.memmove(actionIface, iface);
    atkActionCB_get_keybinding = new Callback(this, "atkAction_get_keybinding", 2);
    actionIface.get_keybinding = atkActionCB_get_keybinding.getAddress();
    atkActionCB_get_name = new Callback(this, "atkAction_get_name", 2);
    actionIface.get_name = atkActionCB_get_name.getAddress();
    ATK.memmove(iface, actionIface);
    return 0;
  }
}

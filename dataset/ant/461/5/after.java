class PlaceHold {
  public void testTypedAdderReference() throws Exception {
    final DefaultConfiguration config = new DefaultConfiguration("test", "test");
    config.setAttribute("my-role1-ref", "id");
    final DefaultConfiguration child = new DefaultConfiguration("my-role1-ref", "test");
    child.setAttribute("id", "id2");
    config.addChild(child);
    final RoleManager roleMgr = ((RoleManager) (getServiceManager().lookup(ROLE)));
    roleMgr.addNameRoleMapping("my-role1", MyRole1.class.getName());
    m_context.setProperty("id", new MyType1());
    m_context.setProperty("id2", new MyType2());
    final ConfigTest6 test = new ConfigTest6();
    m_configurer.configure(test, config, m_context);
    final ConfigTest6 expected = new ConfigTest6();
    expected.add(new MyType1());
    expected.add(new MyType2());
    assertEquals(expected, test);
  }
}

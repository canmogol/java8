package com.fererlab.java8;

public interface IModuleOne {

    String sayHi(String name);

    static IModuleOne getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    enum Singleton {
        INSTANCE;

        private IModuleOne instance;

        Singleton() {
            System.out.println("--------- Singleton");
            try {
                Class<? extends IModuleOne> moduleOneClass = java.lang.Class.forName("com.fererlab.java8.ModuleOne").asSubclass(IModuleOne.class);
                instance = moduleOneClass.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                instance = new IModuleOneMock();
            }
        }

        public IModuleOne getInstance() {
            System.out.println("--------- instance: " + instance);
            return instance;
        }

        private class IModuleOneMock implements IModuleOne {
            @Override
            public String sayHi(String name) {
                return "!!!Mocking";
            }
        }
    }
}

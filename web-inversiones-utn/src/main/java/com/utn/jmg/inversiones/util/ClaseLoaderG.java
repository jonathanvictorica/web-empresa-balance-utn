package com.utn.jmg.inversiones.util;

import java.io.FileInputStream;

public class ClaseLoaderG extends ClassLoader {

    final String basePath = "C:/Users/Guille/workspace/grupo4/InversionesWeb/ModuloPrincipal/com/inversiones/util/";

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        String fullName = name.replace('.', '/');
        fullName += ".class";

        String path = basePath + fullName ;
        try {
            FileInputStream fis = new FileInputStream(path);
            byte[] data = new byte[fis.available()];
            fis.read(data);
            Class<?> res = defineClass(name, data, 0, data.length);
            fis.close();

            return res;
        } catch(Exception e) {
            return super.findClass(name);
        }
    }
}
